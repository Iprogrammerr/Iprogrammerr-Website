const TOUCH_EVENT = "touchstart";
const HOVER_CLASS = "hover";
const NO_HOVER = "no-hover";

const removeHover = () => {
    let hovers = [];
    for (let h of document.getElementsByClassName(HOVER_CLASS)) {
        hovers.push(h);
    }
    for (let h of hovers) {
        h.classList.replace(HOVER_CLASS, NO_HOVER);
    }
    let links = document.querySelectorAll("a");
    for (let a of links) {
        a.className = NO_HOVER;
    }
    window.removeEventListener(TOUCH_EVENT, removeHover);
};

window.addEventListener(TOUCH_EVENT, removeHover);
