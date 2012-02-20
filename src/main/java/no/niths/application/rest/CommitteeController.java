package no.niths.application.rest;

import java.util.ArrayList;

import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.lists.CommitteeList;
import no.niths.common.AppConstants;
import no.niths.common.ValidationHelper;
import no.niths.domain.Committee;
import no.niths.domain.Student;
import no.niths.services.CommitteeService;
import no.niths.services.StudentService;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * 	This class provides CRUD actions in RESTstyle <br />
 * 
 * 	Mapping :<br />
 * 	host:port/committees<br />
 * 	
 *	Headers : <br />
 *	Accept:application/json<br />
 *	Content-Type:appilcation/json <br />
 *	Accept:application/xml<br />
 *	Content-Type:appilcation/xml<br />
 *	
 */
@Controller
@RequestMapping(AppConstants.COMMITTEES)
public class CommitteeController implements RESTController<Committee> {

	@Autowired
	private CommitteeService committeeService;

	@Autowired
	private StudentService studentService;

	Logger logger = org.slf4j.LoggerFactory
			.getLogger(CommitteeController.class);

	private CommitteeList committeeList = new CommitteeList();

	/**
	 * 
	 * @param Committee
	 *            The committee to be created
	 */
	@Override
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED, reason = "Committe created")
	public void create(@RequestBody Committee domain) {
		getService().create(domain);
	}

	/**
	 * Returns the committee by a given id
	 * @param Long id
	 */
	@Override
	@RequestMapping(value = "{id}", method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public Committee getById(@PathVariable Long id) {
		Committee committee = getService().getById(id);
		ValidationHelper.isObjectNull(committee);
		return committee;
	}

	/**
	 * Return all committees if no parameters is given. 
	 * Accepted URLs
	 * (?name=name), (?description=a description) and (?name=name&description=description)<br />
	 * If the URLs is provided and matches the fields in @See no.niths.domain.Committee Committee.class 
	 * The result will be narrowed down to the search criteria
	 * @param committee 
	 */
	@Override
	@RequestMapping(method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public ArrayList<Committee> getAll(Committee committee) {
		committeeList.clear();
		committeeList.addAll(getService().getAll(committee));
		committeeList.setData(committeeList);
		
		ValidationHelper.isListEmpty(committeeList);
		
		return committeeList;
	}

	/**
	 * 
	 * @param committee
	 *            The committee to update
	 * @param id The id to the selected committee
	 */
	@Override
	@RequestMapping(value = { "{id}" }, method = RequestMethod.PUT, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseStatus(value = HttpStatus.OK, reason = "Update ok")
	public void update(@RequestBody Committee committee, @PathVariable Long id) {

		// If the ID is only provided through the URL.
		if (id != null) {
			committee.setId(id);
		}

		getService().update(committee);
	}

	/**
	 * Adds a leader to a committee
	 * 
	 * @param committeeId
	 *            The id of the committee to add leader to
	 * @param studentId
	 *            The id of the student to add as leader
	 */
	@RequestMapping(value = { "addLeader/{committeeId}/{studentId}" }, method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = "Leader added to committee")
	public void addLeader(@PathVariable Long committeeId,
			@PathVariable Long studentId) {
		Committee committee = committeeService.getById(committeeId);
		
		ValidationHelper.isObjectNull(committee);
		Student student = studentService.getStudentById(studentId);
		ValidationHelper.isObjectNull(student);
		committee.getLeaders().add(student);
		committeeService.update(committee);
	}

	/**
	 * Removes a leader from a committee
	 * 
	 * @param committeeId
	 *            The id of the committee to remove leader from
	 * @param studentId
	 *            The id of the student to remove
	 */
	@RequestMapping(value = { "removeLeader/{committeeId}/{studentId}" }, method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = "Leader added to committee")
	public void removeLeader(@PathVariable Long committeeId,
			@PathVariable Long studentId) {
		Committee committee = committeeService.getById(committeeId);

		ValidationHelper.isObjectNull(committee);

		Student studentLeader = studentService.getStudentById(studentId);
		ValidationHelper.isObjectNull(studentLeader);
		ValidationHelper.isStudentLeaderInCommittee(committee, studentLeader);
		committee.getLeaders().remove(studentLeader);
		committeeService.update(committee);
	}

	/**
	 * 
	 * @param long The id of the object to delete
	 */
	@Override
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK, reason = "Committe deleted")
	public void delete(@PathVariable Long id) {
		if (!getService().delete(id)) {
			throw new ObjectNotFoundException();
		}
	}

	/**
	 * 
	 * @return
	 */
	public CommitteeService getService() {
		return committeeService;
	}

	/**
	 * 
	 * @param service
	 */
	public void setService(CommitteeService service) {
		this.committeeService = service;
	}

	/**
	 * Catches constraint violation exceptions Ex: Leader already added to
	 * committee
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Already added")
	public void notUniqueObject() {
	}

	
}
