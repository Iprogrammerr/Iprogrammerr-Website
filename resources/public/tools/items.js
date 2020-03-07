export default class Items {

    constructor() {
        this._source = [];
    }

    add(item) {
        this._source.push(item);
    }

    remove(item) {
        this._source = this._source.filter(e => e != item);
    }

    get value() {
        return this._source;
    }

    get size() {
        return this._source.length;
    }
}