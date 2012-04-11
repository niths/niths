package no.niths.application.rest.lists;

import no.niths.common.AppConstants;
import no.niths.domain.Exam;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = AppConstants.EXAMS)
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