const HIDDEN_CLASS = "hidden";
const VISIBLE_CLASS = "visible";
const ID_ATTRIBUTE = "data-id";
const SCROLL_POS = "scroll-pos";

const skills = document.getElementsByClassName("skills")[0];
const tags = skills.querySelectorAll("h2");
const lists = skills.querySelectorAll("ul");

for (let i = 0; i < tags.length - 1; i++) {
    tags[i].onclick = () => toggleVisibility(lists[i]);
}

tags[tags.length - 1].onclick = () => saveScrollAndExecute(() => location.href = "skills");

const experience = document.getElementsByClassName("experience")[0].children;

for (let i = 0; i < experience.length; i += 2) {
    let url = experienceUrl(experience[i].getAttribute(ID_ATTRIBUTE));
    experience[i].onclick = () => saveScrollAndExecute(() => location.href = url);
    experience[i + 1].onclick = () => saveScrollAndExecute(() => location.href = url);
}

const links = document.querySelectorAll("a");
links.forEach(e => sessionStorage.setItem(SCROLL_POS, window.pageYOffset));

let scrollPos = sessionStorage.getItem(SCROLL_POS);
if (scrollPos == null) {
    scrollPos = 0;
}
if (scrollPos > 0) {
    window.scrollTo(0, scrollPos);
}

function toggleVisibility(element) {
    if (element.className == HIDDEN_CLASS) {
        element.className = VISIBLE_CLASS;
    } else {
        element.className = HIDDEN_CLASS;
    }
};

function saveScrollAndExecute(func) {
    sessionStorage.setItem(SCROLL_POS, window.pageYOffset);
    func();
};

function experienceUrl(id) {
    return `experience/${id}`;
};