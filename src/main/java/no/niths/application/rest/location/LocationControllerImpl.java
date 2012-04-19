package no.niths.application.rest.location;

import no.niths.application.rest.AbstractRESTControllerImpl;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.lists.LocationList;
import no.niths.application.rest.location.interfaces.LocationController;
import no.niths.common.AppNames;
import no.niths.domain.location.Location;
import no.niths.services.interfaces.GenericService;
import no.niths.services.location.interfaces.LocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(AppNames.LOCATIONS)
public class LocationControllerImpl extends AbstractRESTControllerImpl<Location>
implements LocationController {

	@Autowired
	private LocationService service;
	
	private LocationList list = new LocationList();
	
	@Override
	public GenericService<Location> getService() {
		return service;
	}

	@Override
	public ListAdapter<Location> getList() {
		return list;
	}
}
