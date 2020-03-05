export default class MutableAttributes {

    constructor() {
        this._source = new Map();
    }

    addAtribute(name, value='') {
        this._source.set(name, value);
        console.log("Adding attr", this._source);
    }

    renameAttribute(previousName, newName) {
        const previous = this._source.get(previousName);
        console.log(`Previous attribute of name ${previousName}: ${previous}`);
        if (previous != null) {
            this._source.delete(previousName);
            this._source.set(newName, previous);
            console.log('New name', this._source.get(newName));
        }
    }

    removeAttribute(name) {
        this._source.delete(name);
        console.log("Removing attr", this._source);
    }
};