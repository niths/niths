package no.niths.application.rest.lists;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.AppConstants;
import no.niths.domain.school.Course;

@XmlRootElement(name = AppConstants.COURSES)
public class CourseList extends ListAdapter<Course> {

    private static final long serialVersionUID = 8082915037044141181L;

    @SuppressWarnings("unused")
    @XmlElement(name = "course")
    private List<Course> courseData;

    @Override
    public void setData(List<Course> data) {
        this.courseData = data;
    }
}