package no.niths.application.rest;

import java.util.ArrayList;

import no.niths.application.rest.interfaces.FadderGroupController;
import no.niths.application.rest.lists.FadderGroupList;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.AppConstants;
import no.niths.domain.FadderGroup;
import no.niths.services.interfaces.FadderGroupService;
import no.niths.services.interfaces.GenericService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * Controller for subjects
 *
 */
@Controller
@RequestMapping(AppConstants.FADDER_GROUP)
public class FadderGroupControllerImpl extends AbstractRESTControllerImpl<FadderGroup> implements FadderGroupController{

	private static final Logger logger = LoggerFactory
			.getLogger(FadderGroupControllerImpl.class);

	@Autowired
	private FadderGroupService service;

	private FadderGroupList fadderGroupList = new FadderGroupList();
	
	/**
	 * {@inheritDoc}
	 * 
	 * @return List of all fadder groups and their leaders
	 * 
	 */
	@Override
	@RequestMapping(method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public ArrayList<FadderGroup> getAll(FadderGroup domain) {
		fadderGroupList = (FadderGroupList) super.getAll(null);
		for (int i = 0; i < fadderGroupList.size(); i++){
			fadderGroupList.get(i).setFadderChildren(null);
		}
		return fadderGroupList;
	}
	
	@Override
	public void addLeaderToAGroup(Long groupId, Long studId) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void removeLeaderFromAGroup(Long groupId, Long studId) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public GenericService<FadderGroup> getService() {
		return service;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListAdapter<FadderGroup> getList() {
		return fadderGroupList;
	}



}
