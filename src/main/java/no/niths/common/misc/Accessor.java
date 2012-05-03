package no.niths.common.misc;
/**
 * Enum used too clear or fetch relations
 * used in LazyFixer
 *
 */
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