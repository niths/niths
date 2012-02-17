package no.niths.application.rest;

import java.util.ArrayList;

import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.lists.CommitteeList;
import no.niths.common.AppConstants;
import no.niths.domain.Committee;
import no.niths.domain.Student;
import no.niths.services.CommitteeService;
import no.niths.services.StudentService;

import org.hibernate.NonUniqueObjectException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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

	@Override
	@RequestMapping(value = "{id}", method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public Committee getById(@PathVariable Long id) {
		Committee c = getService().getById(id);
		if (c == null) {
			throw new ObjectNotFoundException();
		}
		return c;
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public ArrayList<Committee> getAll(Committee committee) {
		committeeList.clear();
		committeeList.addAll(getService().getAll(committee));
		committeeList.setData(committeeList);
		if (committeeList.size() == 0) {
			throw new ObjectNotFoundException();
		}
		return committeeList;
	}

	/**
	 * 
	 * @param Course
	 *            The Course to update
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
	 * @param committeeId The id of the committee to add leader to
	 * @param studentId The id of the student to add as leader
	 */
	@RequestMapping(value = { "addLeader/{committeeId}/{studentId}" }, method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = "Leader added to committee")
	public void addLeader(@PathVariable Long committeeId, @PathVariable Long studentId) {
		Committee c = committeeService.getById(committeeId);
		if(c == null){
			throw new ObjectNotFoundException("Could not find committee with id: " + committeeId);
		}
		Student s = studentService.getStudentById(studentId);
		if(s == null){
			throw new ObjectNotFoundException("Could not find a student with id: " + studentId);
		}
		c.getLeaders().add(s);
		committeeService.update(c);
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

	public CommitteeService getService() {
		return committeeService;
	}

	public void setService(CommitteeService service) {
		this.committeeService = service;
	}
	
	/**
	 * Catches constraint violation exceptions
	 * Ex: Leader already added to committee
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Already added")
	public void notUniqueObject() {
	}
}
