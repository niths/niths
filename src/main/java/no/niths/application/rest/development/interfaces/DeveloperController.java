package no.niths.application.rest.development.interfaces;

import org.springframework.web.bind.annotation.PathVariable;

import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.interfaces.GenericRESTController;
import no.niths.domain.development.Developer;
/**
 * Controller for developer
 * has the basic CRUD methods and
 * methods too add and remove application
 * and enable and disable developer
 * in addition too method for resetDeveloperKey,
 *
 * For the URL too get Developer add /developers
 * after the {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}
 */
public interface DeveloperController extends GenericRESTController<Developer> {
    
    /**
     * Adds a app to a developer
     *
     * Too add application add /{devId}/application/{applicationId}
     * too the URL
     *
     * Use the POST method
     *
     * @param devId id for the developer
     * @param applicationId id for the application
     */
    void addApplication(Long devId, Long applicationId);
    
    /**
     * Removes a app from a developer
     *
     * Too remove application add /{devId}/application/{applicationId}
     * too the URL
     *
     * Use the DELETE method
     *
     * @param devId id for the developer
     * @param applicationId id for the application
     */
    void removeApplication(Long devId, Long applicationId);

    /**
     * Enables a developer
     * <p>
     * Developer must be enabled to do request
     * <p>
     *
     * Too enable the developer add /{developerId}/enable
     * too the URL
     *
     * Use the PUT method
     *
     * @param developerId id of the developer
     * @throws ObjectNotFoundException if no developer is found
     */
    void enableDeveloper(Long developerId);

    /**
     * Reset developer key
     *
     * Too reset the developer key add /{developerId}/resetDeveloperKey
     * too the URL
     *
     * Use the PUT method
     *
     * @param developerId id of the developer
     * @throws ObjectNotFoundException id no dev is found
     */
    void resetDeveloperKey(Long developerId);
    
    
    /**
     * Disables a developer, so he can't make request
     *
     * Too disable the application add /{developerId}/disable
     * too the URL
     *
     * Use the PUT method
     *
     * @param developerId
     */
    void disableDeveloper(@PathVariable Long developerId);

}
