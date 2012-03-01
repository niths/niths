package no.niths.application.rest;

import java.util.ArrayList;

import no.niths.application.rest.interfaces.FadderGroupController;
import no.niths.application.rest.lists.FadderGroupList;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.AppConstants;
import no.niths.common.ValidationHelper;
import no.niths.domain.FadderGroup;
import no.niths.domain.Student;
import no.niths.services.interfaces.FadderGroupService;
import no.niths.services.interfaces.GenericService;
import no.niths.services.interfaces.StudentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	
	@Autowired
	private StudentService studService;

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

	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequestMapping(value = { "addLeader/{groupId}/{studId}" }, method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK, reason = "Leader added")
	public void addLeaderToAGroup(@PathVariable Long groupId, @PathVariable Long studId) {
		FadderGroup group = service.getById(groupId);
		ValidationHelper.isObjectNull(group);
		Student stud = studService.getById(studId);
		ValidationHelper.isObjectNull(stud);
		
		service.addLeaderToGroup(stud, group);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequestMapping(value = { "removeLeader/{groupId}/{studId}" }, method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK, reason = "Leader removed")
	public void removeLeaderFromAGroup(@PathVariable Long groupId, @PathVariable Long studId) {
		FadderGroup group = service.getById(groupId);
		ValidationHelper.isObjectNull(group);
		Student stud = studService.getById(studId);
		ValidationHelper.isObjectNull(stud);
		
		service.removeALeaderFromAGroup(stud, group);
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequestMapping(value = { "addChild/{groupId}/{studId}" }, method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK, reason = "Child added")
	public void addChildToAGroup(@PathVariable Long groupId, @PathVariable Long studId) {
		FadderGroup group = service.getById(groupId);
		ValidationHelper.isObjectNull(group);
		Student stud = studService.getById(studId);
		ValidationHelper.isObjectNull(stud);
		
		service.addChildrenToGroup(stud, group);
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequestMapping(value = { "removeChild/{groupId}/{studId}" }, method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK, reason = "Child removed")
	public void removeChildFromAGroup(@PathVariable Long groupId, @PathVariable Long studId) {
		FadderGroup group = service.getById(groupId);
		ValidationHelper.isObjectNull(group);
		Student stud = studService.getById(studId);
		ValidationHelper.isObjectNull(stud);
		
		service.removeAChildrenFromAGroup(stud, group);
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequestMapping(value = { "removeAllChildren/{groupId}" }, method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK, reason = "All children removed")
	public void removeAllChildrenFromAGroup(@PathVariable Long groupId) {
		FadderGroup group = service.getById(groupId);
		ValidationHelper.isObjectNull(group);
		
		service.removeAllChildrenFromGroup(group);
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequestMapping(value = { "removeAllLeaders/{groupId}" }, method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK, reason = "All leaders removed")
	public void removeAllLeadersFromAGroup(@PathVariable Long groupId) {
		FadderGroup group = service.getById(groupId);
		ValidationHelper.isObjectNull(group);
		
		service.removeAllLeadersFromGroup(group);
		
	}



}
