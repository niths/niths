package no.niths.services.location.interfaces;

import java.util.List;

import no.niths.domain.location.Location;
import no.niths.services.interfaces.GenericService;

public interface LocationService extends GenericService<Location>{

    List<Location> getPredefinedLocations();
}