package no.niths.application.rest.development.interfaces;

import java.util.List;

import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.interfaces.GenericRESTController;
import no.niths.domain.development.Application;
/**
 * Controller for application
 * has the basic CRUD methods and
 * methods too enable and disable application
 * in addition too method for getTopApps,
 *
 * For the URL too get Application add /applications
 * after the {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}
 */
public interface ApplicationController extends GenericRESTController<Application> {

    /**
     * Enables an application
     * <p>
     * Applications must be enabled to do request
     * <p>
     *
     * Too enable the application add /{applicationId}/enable
     * too the URL
     *
     * Use the PUT method
     *
     * @param applicationId id of the application
     * @throws ObjectNotFoundException if no application is found
     */
    void enableApplication(Long applicationId);

    /**
     * Disables an application
     *
     * Too disable the application add /{applicationId}/disable
     * too the URL
     *
     * Use the PUT method
     * 
     * @param applicationId id of the application
     * @throws ObjectNotFoundException if no application is found
     */
    void disableApplication(Long applicationId);

    /**
     * Returns a list applications ordered
     * by the number of requests @See {@link Application}
     *
     * Too get the top application add /top/{maxResults}
     * too the URL
     *
     * Use the GET method
     * 
     * @param maxResults number of results
     * @return list with maxResults applications
     */
    List<Application> getTopApps(int maxResults);

}
