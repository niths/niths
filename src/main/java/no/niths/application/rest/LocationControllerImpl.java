package no.niths.application.rest;

import no.niths.application.rest.interfaces.LocationController;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.lists.LocationList;
import no.niths.common.AppConstants;
import no.niths.domain.location.Location;
import no.niths.services.interfaces.GenericService;
import no.niths.services.interfaces.LocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(AppConstants.LOCATIONS)
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
