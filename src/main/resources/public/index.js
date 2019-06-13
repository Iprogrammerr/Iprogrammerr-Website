const HIDDEN_CLASS = "hidden";
const VISIBLE_CLASS = "visible";

const skills = document.getElementsByClassName("skills")[0];
const tags = skills.querySelectorAll("h2");
const lists = skills.querySelectorAll("ul");

for (let i = 0; i < tags.length - 1; i++) {
    tags[i].onclick = () => toggleVisibility(lists[i]);
}

function toggleVisibility(element) {
    if (element.className == HIDDEN_CLASS) {
        element.className = VISIBLE_CLASS;
    } else {
        element.className = HIDDEN_CLASS;
    }
}