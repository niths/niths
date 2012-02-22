package no.niths.application.rest.lists;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.AppConstants;
import no.niths.domain.Subject;

@XmlRootElement(name = AppConstants.SUBJECTS)
public class SubjectList extends ListAdapter<Subject>{

    /**
	 * 
	 */
	private static final long serialVersionUID = 7386282831236634626L;
	@XmlElement(name = "subject")
    private List<Subject> data;

    @Override
    public void setData(List<Subject> data) {
     this.data = data;
    }
}
