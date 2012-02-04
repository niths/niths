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

    private CourseList courseList = new CourseList();


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
            value   = "{id}",
            method  = RequestMethod.GET,
            headers = RESTConstants.ACCEPT_HEADER)
    @ResponseBody
    public Course getById(@PathVariable Long id) {
        return service.getCourseById(id);
    }

    /**
     * 
     * @return All courses
     */
    @Override
    @RequestMapping(
            method  = RequestMethod.GET,
            headers = RESTConstants.ACCEPT_HEADER)
    @ResponseBody
    public ArrayList<Course> getAll(Course course, HttpEntity<byte[]> request) {

        // When there is no valid query string
        if (course.isEmpty()) {
            final String FIRST =
                    request.getHeaders().getFirst(RESTConstants.ACCEPT);

            if (FIRST.equals(RESTConstants.JSON)) {
                return (ArrayList<Course>) service.getAllCourses();
            } else if (FIRST.equals(RESTConstants.XML)) {
                courseList.setData(service.getAllCourses());
                return courseList;
            }
        } else {
            // TODO
            // Find and return the course
        }

        return null;
    }

    /**
     * 
     * @param Course The Course to update
     */
    @Override
    @RequestMapping(
            value   = {"", "{id}"},
            method  = RequestMethod.PUT,
            headers = RESTConstants.CONTENT_TYPE_HEADER)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(
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
    @Override
    @RequestMapping(
            value  = "{id}",
            method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        service.deleteCourse(id);
    }
}