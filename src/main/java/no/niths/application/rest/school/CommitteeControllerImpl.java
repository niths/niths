package no.niths.application.rest.school;

import javax.servlet.http.HttpServletResponse;

import no.niths.application.rest.AbstractRESTControllerImpl;
import no.niths.application.rest.lists.CommitteeList;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.school.interfaces.CommitteeController;
import no.niths.common.AppNames;
import no.niths.common.SecurityConstants;
import no.niths.domain.school.Committee;
import no.niths.services.interfaces.GenericService;
import no.niths.services.school.interfaces.CommitteeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class provides CRUD actions in RESTstyle <br />
 * 
 * Mapping :<br />
 * host:port/committees<br />
 * 
 * Headers : <br />
 * Accept:application/json<br />
 * Content-Type:appilcation/json <br />
 * Accept:application/xml<br />
 * Content-Type:appilcation/xml<br />
 * 
 */
@Controller
@RequestMapping(AppNames.COMMITTEES)
public class CommitteeControllerImpl extends
		AbstractRESTControllerImpl<Committee> implements CommitteeController {

	@Autowired
	private CommitteeService committeeService;

	private CommitteeList committeeList = new CommitteeList();

	/**
	 * {@inheritDoc}
	 */
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_SR_COMMITTEE_LEADER)
	public void delete(@PathVariable long id) {
		super.delete(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_SR_COMMITTEE_LEADER)
	public void create(@RequestBody Committee domain, HttpServletResponse res) {
		super.create(domain, res);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_SR_COMMITTEE_LEADER)
	public void update(@RequestBody Committee domain) {
		super.update(domain);
	}

	/**
	 * Adds a leader to a committee
	 * 
	 * @param committeeId
	 *            The id of the committee
	 * @param studentId
	 *            The id of the student to add as leader
	 */
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_SR_COMMITTEE_LEADER)
	@RequestMapping(value = "{committeeId}/add/leader/{studentId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = "Leader added to committee")
	public void addLeader(@PathVariable Long committeeId,
			@PathVariable Long studentId) {
		committeeService.addLeader(committeeId, studentId);
	}

	/**
	 * Removes a leader from a committee
	 * 
	 * @param committeeId
	 *            The id of the committee to remove leader from
	 * @param studentId
	 *            The id of the student to remove
	 */
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_SR_COMMITTEE_LEADER)
	@RequestMapping(value = "{committeeId}/remove/leader/{studentId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = "Deleted")
	public void removeLeader(@PathVariable Long committeeId,
			@PathVariable Long studentId) {
		committeeService.removeLeader(committeeId, studentId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_SR_COMMITTEE_LEADER)
	@RequestMapping(value = "{committeeId}/add/event/{eventId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = "Event added")
	public void addEvent(@PathVariable Long committeeId,
			@PathVariable Long eventId) {
		committeeService.addEvent(committeeId, eventId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_SR_COMMITTEE_LEADER)
	@RequestMapping(value = "{committeeId}/remove/event/{eventId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = "Event removed")
	public void removeEvent(@PathVariable Long committeeId,
			@PathVariable Long eventId) {
		committeeService.removeEvent(committeeId, eventId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GenericService<Committee> getService() {
		return committeeService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListAdapter<Committee> getList() {
		return committeeList;
	}
}
