package no.niths.application.rest.school;

import no.niths.application.rest.AbstractRESTControllerImpl;
import no.niths.application.rest.lists.FeedList;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.school.interfaces.FeedController;
import no.niths.common.constants.AppNames;
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

@Controller
@RequestMapping(AppNames.FEEDS)
public class FeedControllerImpl extends AbstractRESTControllerImpl<Feed>
		implements FeedController {

	private static final String STUDENT_REMOVED = "Student removed";
	private static final String LOCATION_ADDED = "Location Added";
	private static final String LOCATION_REMOVED = "Location removed";

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
	@PreAuthorize(SecurityConstants.ALL_LEADERS)
	@RequestMapping(value = "{feedId}/add/location/{locationId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = LOCATION_ADDED)
	public void addLocation(@PathVariable Long feedId,
			@PathVariable Long locationId) {
		service.addLocation(feedId, locationId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@PreAuthorize(SecurityConstants.ALL_LEADERS)
	@RequestMapping(value = "{feedId}/remove/location", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = LOCATION_REMOVED)
	public void removeLocation(@PathVariable Long feedId) {
		service.removeLocation(feedId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@PreAuthorize(SecurityConstants.ALL_LEADERS)
	@RequestMapping(value = "{feedId}/add/student/{studentId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = "STUDENT_ADDED_TO_FEED")
	public void addStudent(@PathVariable Long feedId,
			@PathVariable Long studentId) {
		service.addStudent(feedId, studentId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@PreAuthorize(SecurityConstants.ALL_LEADERS)
	@RequestMapping(value = "{feedId}/remove/student", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = STUDENT_REMOVED)
	public void removeStudent(@PathVariable Long feedId) {
		service.removeStudent(feedId);
	}
}
