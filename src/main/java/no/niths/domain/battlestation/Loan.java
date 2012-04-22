package no.niths.domain.battlestation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import no.niths.common.constants.AppNames;
import no.niths.domain.Domain;
import no.niths.domain.adapter.JsonCalendarDeserializerAdapter;
import no.niths.domain.adapter.JsonCalendarSerializerAdapter;
import no.niths.domain.adapter.XmlCalendarAdapter;
import no.niths.domain.school.Student;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@XmlRootElement
@Entity
@Table(name = AppNames.LOANS)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@XmlAccessorType(XmlAccessType.FIELD)
public class Loan implements Domain {

	@Transient
	private static final long serialVersionUID = 3498079752190376516L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "loan_date")
	@Temporal(TemporalType.TIMESTAMP)
	@XmlSchemaType(name = "date")
	@XmlJavaTypeAdapter(XmlCalendarAdapter.class)
	@XmlElement(name="starttime")
	private Calendar startTime;

	@Column(name = "return_date")
	@Temporal(TemporalType.TIMESTAMP)
	@XmlSchemaType(name = "date")
	@XmlJavaTypeAdapter(XmlCalendarAdapter.class)
	@XmlElement(name="endtime")
	private Calendar endTime;

	@OneToMany(fetch = FetchType.LAZY, targetEntity = Console.class)
	@JoinTable(name = "loans_consoles", joinColumns = @JoinColumn(name = "loan_id"), inverseJoinColumns = @JoinColumn(name = "console_id"))
	@Cascade(CascadeType.ALL)
	private List<Console> consoles = new ArrayList<Console>();

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Student.class)
	@JoinTable(name = "students_loans", joinColumns = @JoinColumn(name = "loan_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private Student student;

	public Loan() {
		this(null, null);
		setConsoles(null);
		setStudent(null);
	}

	public Loan(GregorianCalendar loanDate) {
		setStartTime(loanDate);
	}

	public Loan(GregorianCalendar startTime, GregorianCalendar endTime) {
		setStartTime(startTime);
		setEndTime(endTime);
	}

	public Loan(Long loanId) {
		setId(loanId);
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getId() {
		return id;
	}

	@JsonDeserialize(using = JsonCalendarDeserializerAdapter.class)
	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	@JsonSerialize(using = JsonCalendarSerializerAdapter.class)
	public Calendar getStartTime() {
		return startTime;
	}

	@JsonDeserialize(using = JsonCalendarDeserializerAdapter.class)
	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}

	@JsonSerialize(using = JsonCalendarSerializerAdapter.class)
	public Calendar getEndTime() {
		return endTime;
	}

	public void setConsoles(List<Console> consoles) {
		this.consoles = consoles;
	}

	public List<Console> getConsoles() {
		return consoles;
	}

	@JsonSerialize(as = Student.class)
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public boolean equals(Object that) {
		if (!(that instanceof Loan))
			return false;
		Loan loan = (Loan) that;

		return loan == this || loan.getId() == id;
	}

	@JsonIgnore
	public boolean isEmpty() {
		return (id == null && startTime == null && endTime == null);
	}

	@Override
	public String toString() {

		StringBuffer buffer = new StringBuffer();
		buffer.append(String.format("[%s]", id));
		buffer.append(String.format("[%s]", (startTime == null) ? null
				: startTime.getTime()));
		buffer.append(String.format("[%s]", (endTime == null) ? null
				: endTime.getTime()));
		return buffer.toString();

	}
}
