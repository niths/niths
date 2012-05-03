package no.niths.application.rest.helper;

/**
 * Enum for status
 */
public enum Status {

    UPDATED("updated");

    private String msg;

    Status(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}