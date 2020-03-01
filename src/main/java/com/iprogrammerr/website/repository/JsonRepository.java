package com.iprogrammerr.website.repository;

import com.iprogrammerr.website.file.JsonArrayCache;
import com.iprogrammerr.website.model.IdxId;

public abstract class JsonRepository {

    protected final JsonArrayCache cache;

    public JsonRepository(JsonArrayCache cache) {
        this.cache = cache;
    }

    public int firstId() {
        return IdxId.FIRST_ID;
    }

    public int lastId() {
        return cache.contentLength();
    }
}
