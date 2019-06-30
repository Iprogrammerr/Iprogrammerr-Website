import { IdNavigation } from "./id-navigation.js";

const FOCUSED_IMAGE = "focused-image";
const FOCUSED_IMAGE_HIDDEN = `${FOCUSED_IMAGE}-hidden`;
const HIDDEN_SCROLL = "hidden";
const HIDDEN_CLASS = "hidden";
const IMAGE_URL_ATTRIBUTE = "data-image";

const GALLERY = "gallery";
const GALLERY_THREE = "gallery-three";
const GALLERY_TWO = "gallery-two";
const GALLERY_ONE = "gallery-one";

new IdNavigation("project").setup();

const gallery = document.getElementById(GALLERY);
const images = gallery.children;
if (images.length < 2) {
    gallery.classList.replace(GALLERY, GALLERY_ONE);
} else if (images.length < 3) {
    gallery.classList.replace(GALLERY, GALLERY_TWO);
} else if (images.length < 4) {
    gallery.classList.replace(GALLERY, GALLERY_THREE);
}

const focusedImageContainer = document.getElementById("focused");
const rootScroll = document.documentElement.style.overflow;
focusedImageContainer.onclick = () => {
    focusedImageContainer.className = FOCUSED_IMAGE_HIDDEN;
    document.documentElement.style.overflow = rootScroll;
};

const focusedImage = focusedImageContainer.querySelector("img");

let focusedIdx = -1;
for (let i = 0; i < images.length; i++) {
    images[i].onclick = (e) => {
        focusedImage.src = e.target.getAttribute(IMAGE_URL_ATTRIBUTE);
        document.documentElement.style.overflow = HIDDEN_SCROLL;
        if (focusedImageContainer.className == FOCUSED_IMAGE_HIDDEN) {
            focusedImageContainer.className = FOCUSED_IMAGE;
        }
        focusedIdx = i;
        setGalleryArrows();

    };
}

const previousImage = document.getElementById("previousImage");
const nextImage = document.getElementById("nextImage");
function setGalleryArrows() {
    if (focusedIdx > 0) {
        previousImage.classList.remove(HIDDEN_CLASS);
    } else {
        previousImage.classList.add(HIDDEN_CLASS);
    }
    if (focusedIdx < images.length - 1) {
        nextImage.classList.remove(HIDDEN_CLASS);
    } else {
        nextImage.classList.add(HIDDEN_CLASS);
    }
};

previousImage.onclick = e => {
    images[focusedIdx - 1].click();
    e.stopPropagation();
};
nextImage.onclick = e => {
    images[focusedIdx + 1].click();
    e.stopPropagation();
};