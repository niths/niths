package no.niths.domain.misc;

import javax.validation.constraints.Pattern;


/**
 * Wrapper class for Email
 *
 */
public class Email {

                               // Cheap email regexp for NITH email addresses
    public static final String NITH_EMAIL_REGEXP     = "^[a-z09]+@nith.no$",
                               INVALID_EMAIL_MESSAGE = "Invalid email address";
    
    @Pattern(regexp = NITH_EMAIL_REGEXP, message = INVALID_EMAIL_MESSAGE)
    private String senderName;

    @Pattern(regexp = NITH_EMAIL_REGEXP, message = INVALID_EMAIL_MESSAGE)
    private String[] recipientAddresses;

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

    public String[] getRecipientAddresses() {
        return recipientAddresses;
    }

    public void setRecipientAddresses(String[] recipientAddresses) {
        this.recipientAddresses = recipientAddresses;
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