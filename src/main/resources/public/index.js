const HIDDEN_CLASS = "hidden";
const VISIBLE_CLASS = "visible";
const ID_ATTRIBUTE = "data-id";
const SCROLL_POS = "scroll-pos";

let scrollPos = sessionStorage.getItem(SCROLL_POS);
if (scrollPos == null) {
    scrollPos = 0;
}
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

for (let i = 0; i < experience.length; i += 2) {
    let url = experienceUrl(experience[i].getAttribute(ID_ATTRIBUTE));
    experience[i].onclick = () => location.href = url;
    experience[i + 1].onclick = () => location.href = url;
}

function experienceUrl(id) {
    return `experience/${id}`;
}

if (scrollPos > 0) {
    window.scrollTo(0, scrollPos);
}

window.addEventListener("click", () => {
    if (scrollPos != window.pageYOffset) {
        scrollPos = window.pageYOffset;
        sessionStorage.setItem(SCROLL_POS, scrollPos);
    } 
});
