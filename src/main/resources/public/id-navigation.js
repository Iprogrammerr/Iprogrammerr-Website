export function IdNavigation(baseUrl) {

    const _baseUrl = baseUrl;

    this.setup = () => {
        let pathParts = window.location.pathname.split("/");
        let id = pathParts[pathParts.length - 1];
        if (isNaN(id)) {
            id = 1;
        } else {
            id = parseInt(id);
        }
        let previous = document.getElementById("previous");
        let next = document.getElementById("next");
        if (previous != null) {
            previous.onclick = () => window.location.replace(url(id - 1));
        }
        if (next != null) {
            next.onclick = () => window.location.replace(url(id + 1));
        }
    };

    function url(id) {
        return `/${_baseUrl}/${id}`;
    };
}