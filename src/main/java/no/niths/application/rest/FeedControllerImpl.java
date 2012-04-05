package no.niths.application.rest;

import java.util.ArrayList;

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
import no.niths.services.interfaces.LocationService;
import no.niths.services.interfaces.StudentService;

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

	private Logger logger = org.slf4j.LoggerFactory
			.getLogger(FeedController.class);

	@Autowired
	private FeedService service;

	@Autowired
	private LocationService locService;
	
	@Autowired
	private StudentService studentService;
	
	private FeedList list = new FeedList();

	@Override
	public ArrayList<Feed> getAll(Feed domain) {
		list = (FeedList) super.getAll(domain);
		clearRelations();
		return list;
	}

	@Override
	public ArrayList<Feed> getAll(Feed domain, @PathVariable int firstResult,
			@PathVariable int maxResults) {
		list = (FeedList) super.getAll(domain, firstResult, maxResults);
		clearRelations();
		return list;
	}

	private void clearRelations() {
		for (Feed l : list) {
			l.setStudent(null);
			l.setLocation(null);
		}
	}

	@Override
	public Feed getById(@PathVariable Long id) {
		logger.debug("get by id in controller so good so far " + id);
		Feed feed = super.getById(id);
		if (feed.getStudent() != null) {
			feed.getStudent().setCommittees(null);
			feed.getStudent().setCommitteesLeader(null);
			feed.getStudent().setCourses(null);
			feed.getStudent().setFadderGroup(null);
			feed.getStudent().setGroupLeaders(null);
			feed.getStudent().setFeeds(null);
			feed.getStudent().setRoles(null);
		}
		
		if (feed.getLocation() != null) {
			feed.getLocation().setFeeds(null);
			feed.getLocation().setEvents(null);
		}
		return feed;
	}

	@Override
	public GenericService<Feed> getService() {
		return service;
	}

	@Override
	public ListAdapter<Feed> getList() {
		return list;
	}

	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	@RequestMapping(value = "add/location/{feedId}/{locId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = "Location Added")
	public void addLocation(@PathVariable Long feedId,@PathVariable Long locId) {
		Feed feed = service.getById(feedId);
		ValidationHelper.isObjectNull(feed, "Feed does not exist");
		
		if (feed.getLocation() != null && feed.getLocation().getId() == locId) {
			logger.debug("location exist");
			throw new DuplicateEntryCollectionException("Location exist");
		}
		
		Location location = locService.getById(locId);
		ValidationHelper.isObjectNull(location, "Location does not exist");
		
		feed.setLocation(location);
		service.update(feed);
		logger.debug("Location added to feed");
	}

	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	@RequestMapping(value = "remove/location/{feedId}/{locId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = "Location removed")
	public void removeLocation(@PathVariable Long feedId,@PathVariable Long locId) {
		Feed feed = service.getById(feedId);
		ValidationHelper.isObjectNull(feed, "Event not exist");
		
		boolean isRemoved = false;
		if (feed.getLocation() != null && feed.getLocation().getId() == locId) {
			isRemoved = true;
			feed.setLocation(null);
		}

		if (isRemoved) {
			service.update(feed);
		} else {
			logger.debug("Event not Found");
			throw new ObjectNotFoundException("Event not Found");
		}
	}

	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	@RequestMapping(value = "add/student/{feedId}/{studentId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = "Student Added")
	public void addStudent(@PathVariable Long feedId,@PathVariable Long studentId) {
		Feed feed = service.getById(feedId);
		ValidationHelper.isObjectNull(feed, "Feed does not exist");
		if (feed.getStudent() != null && feed.getStudent().getId() == studentId) {
			logger.debug("Student exist");
			throw new DuplicateEntryCollectionException("Student exist");
		}
		
		Student student = studentService.getById(studentId);
		ValidationHelper.isObjectNull(student, "Student does not exist");
		
		feed.setStudent(student);
		service.update(feed);
		logger.debug("Student added to feed");
	}

	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	@RequestMapping(value = "remove/student/{feedId}/{studentId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = "Student removed")
	public void removeStudent(@PathVariable Long feedId,@PathVariable Long studentId) {
		Feed feed = service.getById(feedId);
		ValidationHelper.isObjectNull(feed, "Event not exist");
		
		boolean isRemoved = false;
		if (feed.getStudent() != null && feed.getStudent().getId() == studentId) {
			isRemoved = true;
			feed.setStudent(null);
		}

		if (isRemoved) {
			service.update(feed);
		} else {
			logger.debug("Event not Found");
			throw new ObjectNotFoundException("Event not Found");
		}
	}
}
