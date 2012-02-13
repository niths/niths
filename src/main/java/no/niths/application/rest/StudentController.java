package no.niths.application.rest;

import java.util.ArrayList;

import no.niths.application.rest.lists.StudentList;
import no.niths.common.AppConstants;
import no.niths.domain.Committee;
import no.niths.domain.Student;
import no.niths.services.StudentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(AppConstants.STUDENTS)
public class StudentController implements RESTController<Student> {

	private static final Logger logger = LoggerFactory
			.getLogger(StudentController.class);

	private StudentList studentList = new StudentList();

	@Autowired
	private StudentService service;
	
	

	/**
	 * 
	 * @return All courses
	 */
	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public ArrayList<Student> getAllByName(@PathVariable String name , HttpEntity<byte[]> request) {
		String[] fullName = name.trim().split(" ");
		Student stud = new Student();
		if (fullName.length > 1) {
			for (int i = 1; i < fullName.length; i++) {
				if (i != 1){
					fullName[i] += " ";
				}
				stud.setLastName(stud.getLastName() + fullName[i]);	
			}
		}
		stud.setFirstName(fullName[0]);
		logger.info("Search for students with name: " + stud.toString());
		return getAll(stud, request);
		
	}
	/**
	 * 
	 * @return All courses
	 */
	@Override
	@RequestMapping(method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public ArrayList<Student> getAll(Student student, HttpEntity<byte[]> request) {
		logger.info(student.toString());

		final String FIRST = request.getHeaders()
				.getFirst(RESTConstants.ACCEPT);

		if (FIRST.equals(RESTConstants.JSON)) {
			return (ArrayList<Student>) service.getAllStudents(student);
		} else if (FIRST.equals(RESTConstants.XML)) {
			studentList.setData(service.getAllStudents(student));
			return studentList;
		}

		return null;

	}

	/**
	 * 
	 * @param Student
	 *            The student to be created
	 */
	@Override
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED, reason = "Student created")
	public void create(@RequestBody Student student) {
		service.createStudent(student);

	}

	/**
	 * 
	 * @param long The students id
	 * @return The student identified by the id
	 */
	@Override
	@RequestMapping(value = "{id}", method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public Student getById(@PathVariable Long id) {
		return service.getStudentById(id);
	}

	/**
	 * 
	 * @param Student
	 *            The student to update
	 */
	@Override
	@RequestMapping(value = { "", "{id}" }, method = RequestMethod.PUT, headers = RESTConstants.CONTENT_TYPE_HEADER)
	@ResponseStatus(value = HttpStatus.OK, reason = "Update ok")
	public void update(@RequestBody Student student, @PathVariable Long id) {
		if (id != null) {
			student.setId(id);
		}
		service.updateStudent(student);

	}

	/**
	 * 
	 * @param Id
	 *            The id of the Student to delete
	 */
	@Override
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK, reason = "Student deleted")
	public void delete(@PathVariable Long id) {
		service.deleteStudent(id);

	}
}
