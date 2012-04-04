package no.niths.external;

import javax.validation.constraints.Pattern;


/**
 * 
 * @author NITHs
 *
 */
public class Email {

                               // Cheap email regexp for NITH email addresses
    public static final String NITH_EMAIL_REGEXP     = "^[a-z09]+@nith.no$",
                               INVALID_EMAIL_MESSAGE = "Invalid email address";
    
    @Pattern(regexp = NITH_EMAIL_REGEXP, message = INVALID_EMAIL_MESSAGE)
    private String senderName;

    @Pattern(regexp = NITH_EMAIL_REGEXP, message = INVALID_EMAIL_MESSAGE)
    private String recipientAddress;

    @Pattern(regexp = "^[\\w]{1,30}$", message = "Invalid subject")
    private String subject;

    @Pattern(regexp = "^.*$", message = "Invalid message body")
    private String body;

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}