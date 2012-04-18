package no.niths.application.rest.school;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import no.niths.application.rest.AbstractRESTControllerImpl;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.lists.SubjectList;
import no.niths.application.rest.school.interfaces.SubjectController;
import no.niths.common.AppConstants;
import no.niths.common.SecurityConstants;
import no.niths.domain.school.Subject;
import no.niths.services.interfaces.GenericService;
import no.niths.services.school.interfaces.SubjectService;

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

	@Autowired
	private SubjectService subjectService;

	private SubjectList subjectList = new SubjectList();

	/**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR)
    @RequestMapping(value = "{subjectId}/add/tutor/{studentId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Tutor added to subject")
    public void addTutor(@PathVariable Long subjectId,
                         @PathVariable Long studentId) {
        subjectService.addTutor(subjectId, studentId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR)
    @RequestMapping(value = "{subjectId}/remove/tutor/{studentId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Tutor removed to subject")
    public void removeTutor(@PathVariable Long subjectId,
                            @PathVariable Long studentId) {
        subjectService.removeTutor(subjectId, studentId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR)
    @RequestMapping(value = "{subjectId}/add/room/{roomId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Room added to subject")
    public void addRoom(@PathVariable Long subjectId,
                         @PathVariable Long roomId) {
        subjectService.addRoom(subjectId, roomId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR)
    @RequestMapping(value = "{subjectId}/remove/room", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Room removed from subject")
    public void removeRoom(@PathVariable Long subjectId) {
        subjectService.removeRoom(subjectId);
    }

//	@Override
//	public Subject getById(@PathVariable Long id) {
//		Subject s = super.getById(id);
////		for (Student stud : s.getTutors()) {
////			stud.setCommittees(null);
////			stud.setCourses(null);
////			stud.setFeeds(null);
////			stud.setLoans(null);
////			stud.setRepresentativeFor(null);
////		}
////		if (s.getRoom() != null) {
////			s.getRoom().setAccessFields(null);
////		}
//		return s;
//	}

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
	public void create(@RequestBody Subject domain, HttpServletResponse res) {
		super.create(domain, res);
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
		return subjectService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListAdapter<Subject> getList() {
		return subjectList;
	}

}
