package no.niths.application.rest.exception;

public class QRCodeException extends Exception {

    private static final long serialVersionUID = 4332079541056789220L;

    private String message;

    public QRCodeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}