package no.niths.application.rest;

import no.niths.common.AppConstants;
import no.niths.common.RESTConstants;
import no.niths.domain.Student;
import no.niths.services.StudentService;

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
}
