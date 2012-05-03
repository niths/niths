package no.niths.application.rest.location.interfaces;

import java.util.List;

import no.niths.application.rest.interfaces.GenericRESTController;
import no.niths.domain.location.Location;
/**
 * Controller for location
 * has the basic CRUD methods
 * in addition too a method for getPredefinedLocations
 *
 * For the URL too get location add /locations
 * after the {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}
 */
public interface LocationController extends GenericRESTController<Location> {

    /**
     * Method for getting predefined (default) locations
     *
     * Too get locations that are predefined add /predefined
     * too the URL
     *
     * Use the GET method
     * 
     * @return the predefined locations
     */
    List<Location> getPredefinedLocations();
}