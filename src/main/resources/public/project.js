import { IdNavigation } from "./id-navigation.js";

const IMAGE_SMALL = "small";
const IMAGE_MEDIUM = "medium";
const IMAGE_LARGE = "large";

const GALLERY = "gallery";
const GALLERY_THREE = "gallery-three";
const GALLERY_TWO = "gallery-two";
const GALLERY_ONE = "gallery-one";

new IdNavigation("project").setup();

let gallery = document.getElementById(GALLERY);
let images = gallery.children;
if (images.length < 2) {
    gallery.classList.replace(GALLERY, GALLERY_ONE);
} else if (images.length < 3) {
    gallery.classList.replace(GALLERY, GALLERY_TWO);
} else if (images.length < 4) {
    gallery.classList.replace(GALLERY, GALLERY_THREE);
}
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