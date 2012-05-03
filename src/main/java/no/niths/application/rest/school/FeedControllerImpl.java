package no.niths.application.rest.school;

import no.niths.application.rest.AbstractRESTControllerImpl;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.lists.school.FeedList;
import no.niths.application.rest.school.interfaces.FeedController;
import no.niths.common.constants.DomainConstantNames;
import no.niths.common.constants.SecurityConstants;
import no.niths.domain.school.Feed;
import no.niths.services.interfaces.GenericService;
import no.niths.services.school.interfaces.FeedService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Controller for feed
 * has the basic CRUD methods and
 * methods too add and remove location,
 * student and committee
 *
 * For the URL too get Feeds add /feeds
 * after the {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}
 *
 * <pre>
 * <h1>This is a feed</h1>
 * <p>
 * The purpose of the feed is to be a Twitter like feed
 * so students can post interesting messages, or something thats happening
 * right now. like <quote>Sale on computer software on komplett.no</quote>
 * </p>
 * </pre>
 */
@Controller
@RequestMapping(DomainConstantNames.FEEDS)
public class FeedControllerImpl extends AbstractRESTControllerImpl<Feed>
		implements FeedController {

	private static final String STUDENT_REMOVED = "Student removed";
	private static final String LOCATION_ADDED = "Location Added";
	private static final String LOCATION_REMOVED = "Location removed";
	private static final String ADMIN_SR_AND_STUDENT_ID = SecurityConstants.ADMIN_AND_SR + " or " +
            "(hasRole('ROLE_STUDENT') and principal.studentId == #domain.id)";
	private static final String COMMITTEE_ADDED = "Committe added";
	private static final String COMMITTEE_REMOVED = "Committe added";
	
	
	@Autowired
	private FeedService service;

	private FeedList list = new FeedList();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GenericService<Feed> getService() {
		return service;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListAdapter<Feed> getList() {
		return list;
	}

	@Override
	@PreAuthorize(ADMIN_SR_AND_STUDENT_ID)
	@RequestMapping(value = "{feedId}/location/{locationId}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK, reason = LOCATION_ADDED)
	public void addLocation(@PathVariable Long feedId,
			@PathVariable Long locationId) {
		service.addLocation(feedId, locationId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override

	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	@RequestMapping(value = "{feedId}/location", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK, reason = LOCATION_REMOVED)
	public void removeLocation(@PathVariable Long feedId) {
		service.removeLocation(feedId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@PreAuthorize(ADMIN_SR_AND_STUDENT_ID)
	@RequestMapping(value = "{feedId}/student/{studentId}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK, reason = "STUDENT_ADDED_TO_FEED")
	public void addStudent(@PathVariable Long feedId,
			@PathVariable Long studentId) {
		service.addStudent(feedId, studentId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	@RequestMapping(value = "{feedId}/student", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK, reason = STUDENT_REMOVED)
	public void removeStudent(@PathVariable Long feedId) {
		service.removeStudent(feedId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_SR_COMMITTEE_LEADER)
	@RequestMapping(value = "{feedId}/committee/{committeeId}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK, reason = COMMITTEE_ADDED)
	public void addCommittee(@PathVariable Long feedId,@PathVariable Long committeeId) {
		service.addCommittee(feedId, committeeId);		
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_SR_COMMITTEE_LEADER)
	@RequestMapping(value = "{feedId}/committee", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK, reason = COMMITTEE_REMOVED)
	public void removeCommittee(@PathVariable Long feedId) {
		service.removeCommittee(feedId);
	}
}
