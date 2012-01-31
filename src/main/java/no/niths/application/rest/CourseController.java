package no.niths.application.rest;

import java.util.List;

import no.niths.common.AppConstants;
import no.niths.common.RESTConstants;
import no.niths.domain.Course;
import no.niths.services.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(AppConstants.COURSES)
public class CourseController {

    @Autowired
    private CourseService service;

    /**
     * 
     * @param String the course's id
     * @return The course identified by the id
     */
    @RequestMapping(
            value    = {"{id}.json", "id/{id}.json"},
            method   = RequestMethod.GET,
            produces = RESTConstants.JSON)
    @ResponseBody()
    public Course getCourseByIdAsJSON(@PathVariable long id) {
        return service.getCourseById(id);
    }

    @RequestMapping(
            value    = {"{id}.xml", "id/{id}.xml"},
            method   = RequestMethod.GET,
            produces = RESTConstants.XML)
    @ResponseBody
    public Course getCourseByIdAsXML(@PathVariable long id) {
        return service.getCourseById(id);
    }

    @RequestMapping(
            value    = "name/{name}.json",
            method   = RequestMethod.GET,
            produces = RESTConstants.JSON)
    @ResponseBody
    public Course getCourseByNameAsJSON(@PathVariable String name) {
        return service.getCourseByName(name);
    }

    @RequestMapping(
            value    = "name/{name}.xml",
            method   = RequestMethod.GET,
            produces = RESTConstants.XML)
    @ResponseBody
    public Course getCourseByNameAsXML(@PathVariable String name) {
        return service.getCourseByName(name);
    }

    @RequestMapping(
            value    = {"", "all.json"},
            method   = RequestMethod.GET,
            produces = RESTConstants.JSON)
    @ResponseBody
    public List<Course> getAllCoursesAsJSON() {
    	return service.getAllCourses();
    }

    @RequestMapping(
            value    = "all.xml",
            method   = RequestMethod.GET,
            produces = RESTConstants.XML)
    @ResponseBody
    public List<Course> getAllCoursesAsXML() {
        return service.getAllCourses();
    }

    @RequestMapping(
            value = "foo",
            method   = RequestMethod.POST,
            headers  = "Accept=application/json")
    public void addCourse(@RequestBody Course course) {
        System.out.println("added course");
    }

    public void update(Course t) {
        // TODO Auto-generated method stub
        
    }

    public void delete(Course t) {
        // TODO Auto-generated method stub
        
    }

    public void delete(String id) {
        // TODO Auto-generated method stub
        
    }



	public Course getAsJSON(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}