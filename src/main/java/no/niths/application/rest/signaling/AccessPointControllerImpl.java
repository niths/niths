package no.niths.application.rest.signaling;

import no.niths.application.rest.AbstractRESTControllerImpl;
import no.niths.application.rest.lists.AccessPointList;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.signaling.interfaces.AccessPointController;
import no.niths.common.constants.AppNames;
import no.niths.domain.signaling.AccessPoint;
import no.niths.services.interfaces.GenericService;
import no.niths.services.signaling.interfaces.AccessPointService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(AppNames.ACCESS_POINTS)
public class AccessPointControllerImpl extends
		AbstractRESTControllerImpl<AccessPoint> implements
		AccessPointController {

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