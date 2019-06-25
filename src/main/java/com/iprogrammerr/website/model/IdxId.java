package com.iprogrammerr.website.model;

public class IdxId {

    public static final int FIRST_ID = 1;
    private final int idx;

    private IdxId(int idx) {
        this.idx = idx;
    }

    public static IdxId fromId(int id, int min, int max, int defaultValue) {
        return new IdxId(validated(id, min, max, defaultValue) - 1);
    }

    public static IdxId fromId(int id, int max) {
        return fromId(id, FIRST_ID, max, FIRST_ID);
    }

    private static int validated(int val, int min, int max, int defaultValue) {
        if (val < min || val > max) {
            return defaultValue;
        }
        return val;
    }

    public static IdxId fromIdx(int idx, int min, int max, int defaultValue) {
        return new IdxId(validated(idx, min, max, defaultValue));
    }

    public static IdxId fromIdx(int idx) {
        return fromIdx(idx, 0, Integer.MAX_VALUE, 0);
    }

    public int id() {
        return idx + 1;
    }

    public int idx() {
        return idx;
    }
}
