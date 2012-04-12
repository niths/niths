package no.niths.application.rest;

import java.util.ArrayList;

import no.niths.application.rest.exception.DuplicateEntryCollectionException;
import no.niths.application.rest.exception.NotInCollectionException;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.interfaces.SubjectController;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.lists.SubjectList;
import no.niths.common.AppConstants;
import no.niths.common.SecurityConstants;
import no.niths.common.ValidationHelper;
import no.niths.domain.Student;
import no.niths.domain.Subject;
import no.niths.domain.location.Room;
import no.niths.services.interfaces.GenericService;
import no.niths.services.interfaces.RoomService;
import no.niths.services.interfaces.StudentService;
import no.niths.services.interfaces.SubjectService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * Controller for subjects
 * 
 */
@Controller
@RequestMapping(AppConstants.SUBJECTS)
public class SubjectControllerImpl extends AbstractRESTControllerImpl<Subject>
		implements SubjectController {

	private static final Logger logger = LoggerFactory
			.getLogger(SubjectControllerImpl.class);

	@Autowired
	private SubjectService service;

	@Autowired
	private StudentService studentService;

    @Autowired
    private RoomService roomService;

	private SubjectList subjectList = new SubjectList();

	/**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR)
    @RequestMapping(value = "add/tutor/{subjectId}/{studentId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Tutor added to subject")
    public void addTutor(@PathVariable Long subjectId,
                         @PathVariable Long studentId) {
        Subject subject = service.getById(subjectId);
        ValidationHelper.isObjectNull(subject, "Subject not found");

        Student student = studentService.getById(studentId);
        ValidationHelper.isObjectNull(student, "Student not found");

        if (!subject.getTutors().contains(student)) {
            subject.getTutors().add(student);
            service.update(subject);
        } else {
            throw new DuplicateEntryCollectionException(
                    "Tutor is already added to the subject");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR)
    @RequestMapping(value = "remove/tutor/{subjectId}/{studentId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Tutor removed to subject")
    public void removeTutor(@PathVariable Long subjectId,
                            @PathVariable Long studentId) {
        Subject subject = service.getById(subjectId);
        ValidationHelper.isObjectNull(subject, "Subject not found");

        boolean isRemoved = false;
        for (int i = 0; i < subject.getTutors().size(); i++) {
            if (subject.getTutors().get(i).getId() == studentId) {
                subject.getTutors().remove(i);
                isRemoved = true;
            }
        }

        if (isRemoved) {
            service.update(subject);
            logger.debug("Tutor removed from subject " + subject.getName());
        } else {
            throw new NotInCollectionException("Student is not a tutor");
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR)
    @RequestMapping(value = "add/room/{subjectId}/{roomId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Room added to subject")
    public void addRoom(@PathVariable Long subjectId,
                         @PathVariable Long roomId) {
        Subject subject = service.getById(subjectId);
        ValidationHelper.isObjectNull(subject, "Subject not found");

        Room room = roomService.getById(roomId);
        ValidationHelper.isObjectNull(room, "Room not found");

        subject.setRoom(room);
        service.update(subject);
        logger.debug("Subject updated");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR)
    @RequestMapping(value = "remove/room/{subjectId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Room removed from subject")
    public void removeRoom(@PathVariable Long subjectId) {
        Subject subject = service.getById(subjectId);
        ValidationHelper.isObjectNull(subject, "Subject not found");

        boolean isRemoved = false;
        if (subject.getRoom() != null) {
            subject.setRoom(null);
            isRemoved = true;
        }

        if (isRemoved) {
            service.update(subject);
            logger.debug("Room removed from subject " + subject.getName());
        } else {
            throw new ObjectNotFoundException("Room not found");
        }

    }

	@Override
	public Subject getById(@PathVariable Long id) {
		Subject s = super.getById(id);
//		for (Student stud : s.getTutors()) {
//			stud.setCommittees(null);
//			stud.setCourses(null);
//			stud.setFeeds(null);
//			stud.setLoans(null);
//			stud.setRepresentativeFor(null);
//		}
//		if (s.getRoom() != null) {
//			s.getRoom().setAccessFields(null);
//		}
		return s;
	}

	@Override
	public ArrayList<Subject> getAll(Subject domain) {
		 super.getAll(domain);
		clearRelations();
		return subjectList;
	}

	@Override
	public ArrayList<Subject> getAll(Subject domain,
			@PathVariable int firstResult, @PathVariable int maxResults) {
		 super.getAll(domain, firstResult,
				maxResults);
		clearRelations();
		return subjectList;
	}

	private void clearRelations() {
		for (int i = 0; i < subjectList.size(); i++) {
			subjectList.get(i).setTutors(null);
			subjectList.get(i).setRoom(null);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	public void create(@RequestBody Subject domain) {
		super.create(domain);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	public void hibernateDelete(@PathVariable long id) {
		super.hibernateDelete(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	public void update(@RequestBody Subject domain) {
		super.update(domain);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GenericService<Subject> getService() {
		return service;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListAdapter<Subject> getList() {
		return subjectList;
	}

}
