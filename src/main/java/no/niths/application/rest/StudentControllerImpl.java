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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(AppConstants.STUDENTS)
public class StudentControllerImpl extends AbstractRESTControllerImpl<Student> implements StudentController{

	private static final Logger logger = LoggerFactory
			.getLogger(StudentControllerImpl.class);

	private StudentList studentList = new StudentList();

	@Autowired
	private StudentService service;


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


	@Override
	public GenericService<Student> getService() {
		return service;
	}

	@Override
	public ListAdapter<Student> getList() {
		return studentList;
	}
}
