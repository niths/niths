package no.niths.application.rest.lists;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.AppConstants;
import no.niths.domain.Course;
import no.niths.domain.Subject;

@XmlRootElement(name = AppConstants.TOPICS)
public class SubjectList extends ArrayList<Subject>
        implements Exportable<Subject> {

    @XmlElement(name = "course")
    private List<Subject> data;

    @Override
    public void setData(List<Subject> data) {
     this.data = data;
    }
}
