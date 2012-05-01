package no.niths.application.rest.lists.school;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.constants.DomainConstantNames;
import no.niths.domain.school.Student;

@XmlRootElement(name = DomainConstantNames.STUDENTS)
@XmlAccessorType(XmlAccessType.FIELD)
public class StudentList extends ListAdapter<Student> {

    private static final long serialVersionUID = 3236993384670095653L;

    @SuppressWarnings("unused")
    @XmlElement(name = "student")
    private List<Student> data;

    @Override
    public void setData(List<Student> list) {
        this.data = list;
    }
}