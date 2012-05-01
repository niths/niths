package no.niths.services.location.interfaces;

import java.util.List;

import no.niths.domain.location.Location;
import no.niths.services.interfaces.GenericService;
/**
 * Service Class for Location
 *
 * <p>
 * Inherits the basic CRUD actions
 * </p>
 */
public interface LocationService extends GenericService<Location>{

    List<Location> getPredefinedLocations();
}
