package no.niths.application.rest;

import no.niths.application.rest.interfaces.AccessPointController;
import no.niths.application.rest.lists.AccessPointList;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.AppConstants;
import no.niths.domain.AccessPoint;
import no.niths.services.interfaces.AccessPointService;
import no.niths.services.interfaces.GenericService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(AppConstants.ACCESS_POINTS)
public class AccessPointControllerImpl
        extends AbstractRESTControllerImpl<AccessPoint>
        implements AccessPointController {

    @Autowired
    private AccessPointService service;

    private AccessPointList accessPointList = new AccessPointList();

    @Override
    public GenericService<AccessPoint> getService() {
        return service;
    }

    @Override
    public ListAdapter<AccessPoint> getList() {
        return accessPointList;
    }
}