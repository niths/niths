package no.niths.application.rest;

import no.niths.application.rest.exception.DuplicateEntryCollectionException;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.interfaces.FeedController;
import no.niths.application.rest.lists.FeedList;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.AppConstants;
import no.niths.common.SecurityConstants;
import no.niths.common.ValidationHelper;
import no.niths.domain.Feed;
import no.niths.domain.Student;
import no.niths.domain.location.Location;
import no.niths.services.interfaces.FeedService;
import no.niths.services.interfaces.GenericService;
import no.niths.services.interfaces.StudentService;
import no.niths.services.location.interfaces.LocationService;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(AppConstants.FEEDS)
public class FeedControllerImpl extends AbstractRESTControllerImpl<Feed>
		implements FeedController {

	private static final String STUDENT_REMOVED = "Student removed";
	private static final String LOCATION_ADDED = "Location Added";
	private static final String LOCATION_EXIST = "Location exist";
	private static final String LOCATION_REMOVED = "Location removed";
	private static final String LOCATION_NOT_FOUND = "Location not Found";
	private static final String STUDENT_EXIST = "Student exist";
	private static final String STUDENT_ADDED_TO_FEED = "Student added to feed";
	private static final String STUDENT_NOT_FOUND = "Student not Found";
	
	private Logger logger = org.slf4j.LoggerFactory
			.getLogger(FeedController.class);

	@Autowired
	private FeedService service;

	@Autowired
	private LocationService locService;
	
	@Autowired
	private StudentService studentService;
	
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
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	@RequestMapping(value = "add/location/{feedId}/{locId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = LOCATION_ADDED)
	public void addLocation(@PathVariable Long feedId,@PathVariable Long locId) {
		Feed feed = service.getById(feedId);
		ValidationHelper.isObjectNull(feed, Feed.class);
		
		if (feed.getLocation() != null && feed.getLocation().getId() == locId) {
			logger.debug(LOCATION_EXIST);
			throw new DuplicateEntryCollectionException(LOCATION_EXIST);
		}
		
		Location location = locService.getById(locId);
		ValidationHelper.isObjectNull(location, Location.class);
		
		feed.setLocation(location);
		service.update(feed);
		logger.debug("Location added to feed");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	@RequestMapping(value = "remove/location/{feedId}/{locId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = LOCATION_REMOVED)
	public void removeLocation(@PathVariable Long feedId) {
		Feed feed = service.getById(feedId);
		ValidationHelper.isObjectNull(feed, Feed.class);
		
		boolean isRemoved = false;
		if (feed.getLocation() != null) {
			isRemoved = true;
			feed.setLocation(null);
		}

		if (isRemoved) {
			service.update(feed);
		} else {
			logger.debug(LOCATION_NOT_FOUND);
			throw new ObjectNotFoundException(LOCATION_NOT_FOUND);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	@RequestMapping(value = "add/student/{feedId}/{studentId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = "STUDENT_ADDED_TO_FEED")
	public void addStudent(@PathVariable Long feedId,@PathVariable Long studentId) {
		Feed feed = service.getById(feedId);
		ValidationHelper.isObjectNull(feed, Feed.class);
		if (feed.getStudent() != null && feed.getStudent().getId() == studentId) {
			logger.debug(STUDENT_EXIST);
			throw new DuplicateEntryCollectionException(STUDENT_EXIST);
		}
		
		Student student = studentService.getById(studentId);
		ValidationHelper.isObjectNull(student, Student.class);
		
		feed.setStudent(student);
		service.update(feed);
		logger.debug(STUDENT_ADDED_TO_FEED);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	@RequestMapping(value = "remove/student/{feedId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = STUDENT_REMOVED)
	public void removeStudent(@PathVariable Long feedId) {
		Feed feed = service.getById(feedId);
		ValidationHelper.isObjectNull(feed, Feed.class);
	
		boolean isRemoved = false;
		if (feed.getStudent() != null) {
			isRemoved = true;
			feed.setStudent(null);
		}

		if (isRemoved) {
			service.update(feed);
		} else {
			logger.debug(STUDENT_NOT_FOUND);
			throw new ObjectNotFoundException(STUDENT_NOT_FOUND);
		}
	}
}
