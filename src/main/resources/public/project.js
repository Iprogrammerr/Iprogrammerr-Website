import { IdNavigation } from "./id-navigation.js";

const IMAGE_SMALL = "small";
const IMAGE_MEDIUM = "medium";
const IMAGE_LARGE = "large";

new IdNavigation("project").setup();

let images = document.getElementsByClassName("gallery")[0].children;
let active = null;
for (let i of images) {
    i.onclick = (e) => {
        if (active != e.target) {
            active = e.target;
            active.className = IMAGE_LARGE;
            for (let i of images) {
                if (i != active) {
                    i.className = IMAGE_SMALL;
                }
            }
        } else if (active == e.target) {
            for (let i of images) {
                i.className = IMAGE_MEDIUM;
            }
            active = null;
        }
    };
}