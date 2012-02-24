package no.niths.application.rest;

import java.util.List;

import no.niths.application.rest.interfaces.StudentController;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.lists.StudentList;
import no.niths.common.AppConstants;
import no.niths.common.ValidationHelper;
import no.niths.domain.Course;
import no.niths.domain.Student;
import no.niths.services.interfaces.GenericService;
import no.niths.services.interfaces.StudentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(AppConstants.STUDENTS)
public class StudentControllerImpl extends AbstractRESTControllerImpl<Student>
		implements StudentController {

	private static final Logger logger = LoggerFactory
			.getLogger(StudentControllerImpl.class);

	private StudentList studentList = new StudentList();

	@Autowired
	private StudentService service;

	/**
	 * {@inheritDoc}
	 */
	@RequestMapping(value = "course", method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public List<Student> getStudentsWithNamedCourse(Course course) {
		String name = course.getName();
		logger.info(name);
		studentList.clear();
		studentList.addAll(service.getStudentsWithNamedCourse(name));
		studentList.setData(studentList); // for xml marshalling

		ValidationHelper.isListEmpty(studentList);

		return studentList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GenericService<Student> getService() {
		return service;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListAdapter<Student> getList() {
		return studentList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequestMapping(value = { 
			"{studentId}/orientation-group/{groupId}" }, method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED, reason = "Created")
	public void addStudentToOrientationGroup(@PathVariable long studentId,
			@PathVariable int groupId) {
		
		logger.info(studentId +  " " + groupId);
		Student student = service.getById(studentId);
		ValidationHelper.isObjectNull(student);
		service.addStudentToMentor(student, groupId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequestMapping(value = "orientation-group", method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public List<Student> getAllOrientationGroups() {
		studentList.clear();
		studentList.addAll(service.getAllMentors());
		studentList.setData(studentList); // for xml marshalling
		ValidationHelper.isListEmpty(studentList);

		return studentList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequestMapping(value = "orientation-group/{groupId}", method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public List<Student> getStudentsInOrientationGroup(@PathVariable int groupId) {
		
		studentList.clear();
		studentList.addAll(service.getMentorsByGroupe(groupId));
		studentList.setData(studentList); // for xml marshalling
		ValidationHelper.isListEmpty(studentList);

		return studentList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequestMapping(value = "{studentId}/orientation-group/{groupId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK, reason = "Deleted")
	public void removeStudentFromOrientationGroup(@PathVariable long studentId,
			@PathVariable int groupId) {
		Student student = getById(studentId);
		ValidationHelper.isObjectNull(student);

		service.removeStudentFromMentorGroup(student, groupId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequestMapping(value = "{studentId}/orientation-group", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK, reason = "Deleted")
	public void removeStudentFromAllOrientationGroups(@PathVariable long studentId) {
		Student student = getById(studentId);
		ValidationHelper.isObjectNull(student);
		service.removeStudentFromAllMentorGroups(student);
	}
}
