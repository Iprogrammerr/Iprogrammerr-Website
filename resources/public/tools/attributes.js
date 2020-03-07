export default class Attributes {

    constructor() {
        this._source = new Map();
    }

    addAtribute(name, value='') {
        this._source.set(name, value);
    }

    removeAttribute(name) {
        this._source.delete(name);
    }

    get size() {
        return this._source.size;
    }
};