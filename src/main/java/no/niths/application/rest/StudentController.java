package no.niths.application.rest;

import java.util.List;

import no.niths.application.rest.lists.StudentList;
import no.niths.common.AppConstants;
import no.niths.domain.Student;
import no.niths.infrastructure.StudentRepositoryImpl;
import no.niths.services.StudentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(AppConstants.STUDENTS)
public class StudentController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(StudentController.class);

    @Autowired
    private StudentService service;

    /**
     * 
     * @param Student The student to be created
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createStudent(@RequestBody Student student) {
        service.createStudent(student);
    }

    /**
     * 
     * @param long The student's id
     * @return The student identified by the id
     */
    @RequestMapping(
            value    = {"{id}.json", "id/{id}.json"},
            method   = RequestMethod.GET,
            produces = RESTConstants.JSON)
    @ResponseBody
    public Student getStudentByIdAsJSON(@PathVariable long id) {
    	logger.debug("Getting student by id: " + id );
        return service.getStudentById(id);
    }

    /**
     * 
     * @param long The student's id
     * @return The student identified by the id
     */
    @RequestMapping(
            value    = {"{id}.xml", "id/{id}.xml"},
            method   = RequestMethod.GET,
            produces = RESTConstants.XML)
    @ResponseBody
    public Student getCourseByIdAsXML(@PathVariable long id) {
        return service.getStudentById(id);
    }

    /**
     * 
     * @param String The student's name
     * @return The student identified by the name
     */
    @RequestMapping(
            value    = "name/{name}.json",
            method   = RequestMethod.GET,
            produces = RESTConstants.JSON)
    @ResponseBody
    public List<Student> getStudentByNameAsJSON(@PathVariable String name) {
        return service.getStudentByName(name);
    }

    /**
     * 
     * @param String The student's name
     * @return The student identified by the name
     */
    @RequestMapping(
            value    = "name/{name}.xml",
            method   = RequestMethod.GET,
            produces = RESTConstants.XML)
    @ResponseBody
    public List<Student> getStudentByNameAsXML(@PathVariable String name) {
        return service.getStudentByName(name);
    }

    @RequestMapping(
            value    = {"", "all.json"},
            method   = RequestMethod.GET,
            produces = RESTConstants.JSON)
    @ResponseBody
    public List<Student> getAllStudentsAsJSON() {
        return service.getAllStudents();
    }

    @RequestMapping(
            value    = "all.xml",
            method   = RequestMethod.GET,
            produces = RESTConstants.XML)
    @ResponseBody
    public StudentList getAllStudentsAsXML() {
        return new StudentList(service.getAllStudents());
    }
}
