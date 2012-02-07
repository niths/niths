package no.niths.infrastructure.interfaces;

import java.util.List;

import no.niths.domain.Course;


public interface CoursesRepository   {

    Long createCourse(Course course);

    Course getCourseById(long id);

    Course getCourseByName(String name);

    List<Course> getAllCourses();

    void updateCourse(Course course);

    boolean deleteCourse(long id);
}