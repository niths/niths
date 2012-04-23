package no.niths.application.rest.lists;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.constants.AppNames;
import no.niths.domain.school.Subject;

@XmlRootElement(name = AppNames.SUBJECTS)
public class SubjectList extends ListAdapter<Subject> {

    private static final long serialVersionUID = 7386282831236634626L;

    @SuppressWarnings("unused")
    @XmlElement(name = "subject")
    private List<Subject> data;

    @Override
    public void setData(List<Subject> data) {
        this.data = data;
    }
}