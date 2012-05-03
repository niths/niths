package no.niths.application.rest.lists.school;

import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.constants.DomainConstantNames;
import no.niths.domain.school.Exam;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
/**
 * Class to contain a list of exams
 */
@XmlRootElement(name = DomainConstantNames.EXAMS)
public class ExamList extends ListAdapter<Exam> {

    private static final long serialVersionUID = 6258293263010726953L;

    @SuppressWarnings("unused")
    @XmlElement(name = "exam")
    private List<Exam> data;

    @Override
    public void setData(List<Exam> exams) {
        this.data = exams;
    }
}