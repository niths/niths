package no.niths.application.rest.location.interfaces;

import java.util.List;

import no.niths.application.rest.interfaces.GenericRESTController;
import no.niths.domain.location.Location;

public interface LocationController extends GenericRESTController<Location> {

    /**
     * Method for getting predefined (default) locations 
     * 
     * @return the predefined locations
     */
    List<Location> getPredefinedLocations();
}