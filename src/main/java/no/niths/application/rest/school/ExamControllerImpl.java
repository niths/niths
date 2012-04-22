package no.niths.application.rest.school;

import no.niths.application.rest.AbstractRESTControllerImpl;
import no.niths.application.rest.lists.ExamList;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.school.interfaces.ExamController;
import no.niths.common.constants.AppNames;
import no.niths.domain.school.Exam;
import no.niths.services.interfaces.GenericService;
import no.niths.services.school.interfaces.ExamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller for exams
 */
@Controller
@RequestMapping(AppNames.EXAMS)
public class ExamControllerImpl extends AbstractRESTControllerImpl<Exam>
		implements ExamController {


	@Autowired
	private ExamService examService;

	private ExamList examList = new ExamList();

	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequestMapping(value = "{examId}/add/room/{roomId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = "Room Added")
	public void addRoom(@PathVariable Long examId, @PathVariable Long roomId) {
		examService.addRoom(examId, roomId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequestMapping(value = "{examId}/remove/room/{roomId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = "Room Removed")
	public void removeRoom(@PathVariable Long examId, @PathVariable Long roomId) {
		examService.removeRoom(examId, roomId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequestMapping(value = "{examId}/add/subject/{subjectId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = "Subject Added")
	public void addSubject(@PathVariable Long examId,
			@PathVariable Long subjectId) {
		examService.addSubject(examId, subjectId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequestMapping(value = "{examId}/remove/subject", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = "Subject Removed")
	public void removeSubject(@PathVariable Long examId) {
		examService.removeSubject(examId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GenericService<Exam> getService() {
		return examService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListAdapter<Exam> getList() {
		return examList;
	}
}
