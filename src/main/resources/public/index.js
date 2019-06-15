const HIDDEN_CLASS = "hidden";
const VISIBLE_CLASS = "visible";

const skills = document.getElementsByClassName("skills")[0];
const tags = skills.querySelectorAll("h2");
const lists = skills.querySelectorAll("ul");

for (let i = 0; i < tags.length - 1; i++) {
    tags[i].onclick = () => toggleVisibility(lists[i]);
}

tags[tags.length - 1].onclick = () => location.href = "skills";

function toggleVisibility(element) {
    if (element.className == HIDDEN_CLASS) {
        element.className = VISIBLE_CLASS;
    } else {
        element.className = HIDDEN_CLASS;
    }
}

const experience = document.getElementsByClassName("experience")[0].children;

for (let i = 0; i < experience.length; i+=2) {
    experience[i].onclick = () => location.href = "experience";
    experience[i + 1].onclick = () => location.href = "experience";
}