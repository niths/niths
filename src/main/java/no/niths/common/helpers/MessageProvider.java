package no.niths.common.helpers;

import no.niths.application.rest.helper.Error;
import no.niths.application.rest.helper.Status;
/**
 * Class used too build error or status messages
 *
 */
public class MessageProvider {

    /**
     * 
     * @param mainDomain the domain which caused the error
     * @param error the type of error
     * @return the string describing the error
     */
    public static String buildErrorMsg(Class<?> mainDomain, Error error) {
        return String.format(
                "%s %s", mainDomain.getSimpleName(), error.getMsg());
    }
    
    /**
     * 
     * @param mainDomain the domain which caused a status change
     * @param status the type of status
     * @return the string describing the status
     */
    public static String buildStatusMsg(Class<?> mainDomain, Status status) {
        return String.format(
                "%s %s", mainDomain.getSimpleName(), status.getMsg());
    }
}
