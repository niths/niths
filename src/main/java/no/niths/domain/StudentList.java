package no.niths.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.AppConstants;

@XmlRootElement(name = AppConstants.STUDENTS)
public class StudentList {

    @XmlElement(name = "student")
    private List<Student> data;

    public StudentList(List<Student> data) {
        this.data = data;
    }
}