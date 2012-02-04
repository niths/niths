package no.niths.application.rest.lists;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.AppConstants;
import no.niths.domain.Course;

@XmlRootElement(name = AppConstants.COURSES)
public class CourseList extends ArrayList<Course>
        implements Exportable<Course> {

    @XmlElement(name = "course")
    private List<Course> data;

    @Override
    public void setData(List<Course> data) {
     this.data = data;
    }
}
