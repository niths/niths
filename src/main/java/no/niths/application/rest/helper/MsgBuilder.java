package no.niths.application.rest.helper;

/**
 * Class that helps building status messages
 */
public class MsgBuilder {

    // TODO: Implement
    public static String buildStatusMsg() {
        return null;
    }

    public static String buildErrorMsg(Class<?> domainClass, Error error) {
        return String.format(
                "%s %s", domainClass.getSimpleName(), error.getMsg());
    }
}