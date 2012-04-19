package no.niths.services.school;

import no.niths.application.rest.helper.Status;
import no.niths.common.MessageProvider;
import no.niths.common.ValidationHelper;
import no.niths.domain.school.Committee;
import no.niths.domain.school.Event;
import no.niths.domain.school.Feed;
import no.niths.domain.school.Student;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.infrastructure.school.interfaces.CommitteeRepositorty;
import no.niths.infrastructure.school.interfaces.EventRepository;
import no.niths.infrastructure.school.interfaces.StudentRepository;
import no.niths.services.AbstractGenericService;
import no.niths.services.school.interfaces.CommitteeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommitteeServiceImpl extends AbstractGenericService<Committee>
		implements CommitteeService {

	private Logger logger = LoggerFactory.getLogger(CommitteeServiceImpl.class);

	@Autowired
	private CommitteeRepositorty repo;

	@Autowired
	private EventRepository eventRepo;

	@Autowired
	private StudentRepository studentRepo;

	@Override
	public GenericRepository<Committee> getRepository() {
		return repo;
	}

	@Override
	public void addLeader(Long committeeId, Long studentId) {
		Committee committee = validate(repo.getById(committeeId),
				Committee.class);
		checkIfObjectIsInCollection(committee.getLeaders(), studentId,
				Student.class);

		Student student = studentRepo.getById(studentId);
		ValidationHelper.isObjectNull(student, Student.class);

		committee.getLeaders().add(student);
		logger.debug(MessageProvider.buildStatusMsg(Feed.class, Status.UPDATED));
	}

	@Override
	public void removeLeader(Long committeeId, Long studentId) {
		Committee committee = validate(repo.getById(committeeId),
				Committee.class);
		checkIfIsRemoved(committee.getLeaders().remove(new Student(studentId)),
				Student.class);
	}

	@Override
	public void addEvent(Long committeeId, Long eventId) {
		Committee committee = validate(repo.getById(committeeId),
				Committee.class);
		checkIfObjectIsInCollection(committee.getEvents(), eventId, Event.class);

		Event event = eventRepo.getById(eventId);
		ValidationHelper.isObjectNull(event, Event.class);

		committee.getEvents().add(event);
		logger.debug(MessageProvider
				.buildStatusMsg(Event.class, Status.UPDATED));
	}

	@Override
	public void removeEvent(Long committeeId, Long eventId) {
		Committee committee = validate(repo.getById(committeeId),
				Committee.class);

		checkIfIsRemoved(committee.getEvents().remove(new Event(eventId)),
				Event.class);
	}
}
