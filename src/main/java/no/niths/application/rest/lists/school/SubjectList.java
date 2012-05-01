package no.niths.application.rest.lists.school;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.constants.DomainConstantNames;
import no.niths.domain.school.Subject;

@XmlRootElement(name = DomainConstantNames.SUBJECTS)
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