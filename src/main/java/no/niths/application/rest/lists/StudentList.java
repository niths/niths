package no.niths.application.rest.lists;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.AppConstants;
import no.niths.domain.Student;

@XmlRootElement(name = AppConstants.STUDENTS)
public class StudentList {

    @XmlElement(name = "student")
    private List<Student> data;

    public StudentList(List<Student> data) {
        this.data = data;
    }
}