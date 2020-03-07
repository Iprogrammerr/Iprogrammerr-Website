import Attributes from "./attributes.js";
import Items from "./items.js";

function initializeItemToCompare(root, itemName) {
    const itemProps = {}
    itemProps[DATA_ITEM_KEY] = itemName;
    const attributesNames = findAttributesWithNames();
    root.appendChild(
        renderElement('div', itemProps,
            renderElement('h2', {}, itemName),
            ...attributesNames.map(e => renderItemAttribute(e[0], e[1]))));
};

function renderAttribute(attribute, name) {
    return renderElement('div', { class: 'text-center' },
        renderElement('input', propertiesWithDataAttribute({
            type: 'text',
            maxlength: MAX_ATTRIBUTE_NAME_LENGTH,
            value: name,
            onkeyup: function (e) {
                const newName = this.value.trim();
                if (newName == '') {
                    return;
                }
                updateAttributeName(extractAttributeName(this), newName);
            }
        }, attribute)),
        renderElement('button', {
            onclick: function (e) {
                if (attributesValues.size > 1) {
                    const parent = this.parentNode;
                    const input = parent.querySelector('input');
                    const attrValue = extractAttributeName(input);
                    const attributesItems = findItemsAttributeOfName(attrValue);
                    attributesItems.forEach(e => e.remove());
                    attributesValues.removeAttribute(attrValue);
                    parent.remove();
                }
            }
        }, '-'),
        renderElement('button', {
            onclick: () => {
                const attribute = nextAttributeKey(true);
                attributesValues.addAtribute(attribute);
                attributes.appendChild(renderAttribute(attribute, attribute));
                findAllToCompareItems().forEach(e => e.appendChild(renderItemAttribute(attribute, attribute)));
            }
        }, '+'));
}

function renderItemToCompare(name) {
    return renderElement('div', {}, renderElement('input', propertiesWithDataAttribute({
        type: 'text',
        maxlength: MAX_ITEM_NAME_LENGTH,
        value: name,
        onkeyup: function (e) {
            const newName = this.value.trim();
            if (newName == '') {
                return;
            }
            const attributeName = extractAttributeName(this, DATA_ITEM_NAME);
            updateItemName(attributeName, newName);
        }
    }, name, DATA_ITEM_NAME), name),
        renderElement('button', {
            onclick: (e) => {
                if (itemsValues.size > 2) {
                    const parent = e.target.parentNode;
                    const name = extractItemName(parent.querySelector('input'));
                    parent.remove();
                    findItemToCompare(name).remove();
                    itemsValues.remove(name);
                }
            }
        }, '-'),
        renderElement('button', {
            onclick: () => {
                const item = nextItemKey(true);
                itemsValues.add(item);
                items.appendChild(renderItemToCompare(item));
                initializeItemToCompare(comparatorBody, item);
            }
        }, '+'));
}

function extractAttributeName(element, attributeKey = DATA_ATTRIBUTE_KEY) {
    return element.getAttribute(attributeKey);
}

function extractItemName(element) {
    return element.getAttribute(DATA_ITEM_NAME);
}

function updateAttributeName(attribute, newName) {
    const attributeElements = findItemsAttributeOfName(attribute);
    attributeElements.forEach(e => {
        Array.from(e.children).forEach(c => {
            const name = c.nodeName.toLowerCase();
            if (name == 'h3') {
                c.innerText = newName;
            } else if (name == 'input' && c.placeholder == '') {
                c.placeholder = newName;
            }
        });
    });
}

function updateItemName(attribute, newName) {
    const item = findItemToCompare(attribute).querySelector('h2');
    item.innerText = newName;
}

function findAllToCompareItems() {
    return document.querySelectorAll(`*[${DATA_ITEM_KEY}]`);
}

function findItemToCompare(name) {
    return document.querySelector(`*[${DATA_ITEM_KEY}='${name}']`);
}

function findItemsAttributeOfName(name) {
    return document.querySelectorAll(`div[${DATA_ATTRIBUTE_KEY}='${name}']`);
}

function findAttributesInputs(root = document) {
    return Array.from(root.querySelectorAll(`input[${DATA_ATTRIBUTE_KEY}]`));
}

function findAttributesNames() {
    return findAttributesInputs().map(e => e.value);
}

function findAttributesWithNames() {
    const attributesNames = [];
    findAttributesInputs(attributes).forEach(e => {
        console.log(e);
        const attribute = extractAttributeName(e);
        const name = e.value;
        attributesNames.push([attribute, name]);
    });
    return attributesNames;
}

function nextItemKey(increment = false) {
    if (increment) {
        ++itemsCounter;
    }
    return `Item${itemsCounter}`;
}

function nextAttributeKey(increment = false) {
    if (increment) {
        ++attributeCounter;
    }
    return `attribute${attributeCounter}`;
}

function renderItemAttribute(attribute, name) {
    return renderElement('div', propertiesWithDataAttribute({ 'class': 'item-attribute' }, attribute),
        renderElement('h3', emptyPropertiesWithDataAttribute(attribute), `${name}`),
        renderElement('input', propertiesWithDataAttribute({ type: 'text', placeholder: 'Value' },
            attribute)));
}

function propertiesWithDataAttribute(properties, attributeValue, attributeKey = DATA_ATTRIBUTE_KEY) {
    properties[attributeKey] = attributeValue;
    return properties;
}

function emptyPropertiesWithDataAttribute(attributeValue, attributeKey = DATA_ATTRIBUTE_KEY) {
    return propertiesWithDataAttribute({}, attributeValue, attributeKey);
}

function renderElement(type, properties, ...children) {
    const dom = document.createElement(type);
    for (const k in properties) {
        const value = properties[k];
        if (typeof value == 'function') {
            dom[k] = value;
        } else {
            dom.setAttribute(k, value);
        }
    }
    for (const child of children) {
        if (typeof child == 'string') {
            dom.appendChild(document.createTextNode(child));
        } else {
            dom.appendChild(child);
        }
    }
    return dom;
}

const attributes = document.getElementById('attributes');
const items = document.getElementById('items');
const comparatorBody = document.getElementsByClassName('comparator')[0];

const DATA_ATTRIBUTE_KEY = 'data-attribute';
const DATA_ITEM_KEY = 'data-item';
const DATA_ITEM_NAME = 'data-item-name';
const MAX_ITEM_NAME_LENGTH = 30;
const MAX_ATTRIBUTE_NAME_LENGTH = 50;

let attributeCounter = 1;
let itemsCounter = 1;

const itemsValues = new Items();
const attributesValues = new Attributes();

const firstAttribute = nextAttributeKey();

attributesValues.addAtribute(firstAttribute);

attributes.appendChild(renderAttribute(firstAttribute, firstAttribute));

itemsValues.add(nextItemKey());
itemsValues.add(nextItemKey(true));
itemsValues.value.forEach(e => {
    items.appendChild(renderItemToCompare(e));
    initializeItemToCompare(comparatorBody, e);
});
