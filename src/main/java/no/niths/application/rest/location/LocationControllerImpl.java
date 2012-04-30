package no.niths.application.rest.location;

import java.util.List;

import no.niths.application.rest.AbstractRESTControllerImpl;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.lists.location.LocationList;
import no.niths.application.rest.location.interfaces.LocationController;
import no.niths.common.constants.DomainConstantNames;
import no.niths.domain.location.Location;
import no.niths.services.interfaces.GenericService;
import no.niths.services.location.interfaces.LocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(DomainConstantNames.LOCATIONS)
public class LocationControllerImpl extends AbstractRESTControllerImpl<Location>
implements LocationController {

    @Autowired
    private LocationService service;
    
    private LocationList list = new LocationList();

    @Override
    @RequestMapping(value = "predefined", method = RequestMethod.GET)
    @ResponseBody
    public List<Location> getPredefinedLocations() {
        return null;
    }

    @Override
    public GenericService<Location> getService() {
        return service;
    }

    @Override
    public ListAdapter<Location> getList() {
        return list;
    }
}