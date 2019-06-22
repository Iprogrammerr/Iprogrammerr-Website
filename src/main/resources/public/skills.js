let pathParts = window.location.pathname.split("/");
let id = pathParts[pathParts.length - 1];
if (isNaN(id)) {
    id = 1;
} else {
    id = parseInt(id);
}
const previous = document.getElementById("previous");
const next = document.getElementById("next");

if (previous != null) {
    previous.onclick = () => window.location.replace(skillsUrl(id - 1));
}
if (next != null) {
   next.onclick = () => window.location.replace(skillsUrl(id + 1));
}

function skillsUrl(id) {
    return `/skills/${id}`;
};