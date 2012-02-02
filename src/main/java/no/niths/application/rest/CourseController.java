package no.niths.application.rest;

import java.util.ArrayList;

import no.niths.application.rest.lists.CourseList;
import no.niths.common.AppConstants;
import no.niths.domain.Course;
import no.niths.services.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(AppConstants.COURSES)
public class CourseController implements RESTController<Course> {

    @Autowired
    private CourseService service;

    /**
     * 
     * @param Course The course to be created
     */
    @Override
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void create(@RequestBody Course course) {
        service.createCourse(course);
    }

    /**
     * 
     * @param long The course's id
     * @return The course identified by the id
     */
    @Override
    @RequestMapping(
            value    = {"{id}", "?id={id}"},
            method   = RequestMethod.GET)
    @ResponseBody()
    public Course getById(@PathVariable long id) {
        return service.getCourseById(id);
    }

    /**
     * 
     * @param String The course's name plus '.json'
     * @return The course identified by the name
     */
    @Override
    @RequestMapping(
            value    = "?name={name}",
            method   = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Course> getByName(@PathVariable String name) {
        return null;
    }

    /**
     * 
     * @param String The course's name plus '.xml'
     * @return The course identified by the name
     */
    @RequestMapping(
            value    = "name/{name}.xml",
            method   = RequestMethod.GET,
            produces = RESTConstants.XML)
    @ResponseBody
    public Course getCourseByNameAsXML(@PathVariable String name) {
        return service.getCourseByName(name);
    }

//    /**
//     * 
//     * @return All courses as JSON
//     */
//    @RequestMapping(
//            value    = {"", "all.json"},
//            method   = RequestMethod.GET,
//            produces = RESTConstants.JSON)
//    @ResponseBody
//    public List<Course> getAllCoursesAsJSON() {
//        return service.getAllCourses();
//    }

    /**
     * 
     * @return All courses as XML
     */
    @RequestMapping(
            value    = "all",
            method   = RequestMethod.GET)
    @ResponseBody
    public CourseList getAllCoursesAsXML(HttpEntity<byte[]> requestEntity) {
//        String requestHeader = requestEntity.getHeaders().getFirst("foo");
//        //System.out.println(requestHeader);
        CourseList list = new CourseList();
        list.setData(service.getAllCourses());
        int asd = 10;
        return list;
    }

    /**
     * 
     * @param Course The Course to update
     */
    @RequestMapping(
            value  = {"", "{id}"},
            method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateCourse(
            @RequestBody Course course,
            @PathVariable Long id) {

        // If the ID is only provided through the URL.
        if (id != null)
            course.setId(id);

        service.updateCourse(course);
    }

    /**
     * 
     * @param long The id of the Course to delete
     */
    @RequestMapping(
            value  = "{id}",
            method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteCourse(@PathVariable long id) {
        service.deleteCourse(id);
    }

    @Override
    public ArrayList<Course> getAll(HttpEntity<byte[]> request) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(Course domain, Long id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(long id) {
        // TODO Auto-generated method stub
        
    }
}