package no.niths.common.misc;

public enum Accessor {

    GET("get"), SET("set");

    private String val;

    Accessor(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val;
    }
}