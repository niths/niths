package no.niths.application.rest.lists;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.AppConstants;
import no.niths.domain.Student;

@XmlRootElement(name = AppConstants.STUDENTS)
public class StudentList extends ListAdapter<Student>
{

	private static final long serialVersionUID = 3236993384670095653L;
	@XmlElement(name = "student")
    private List<Student> data;

	@Override
	public void setData(List<Student> list) {
		this.data = list;
	}
	
	
}
