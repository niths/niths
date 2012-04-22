package no.niths.services.school;

import no.niths.application.rest.helper.Status;
import no.niths.common.helpers.MessageProvider;
import no.niths.common.helpers.ValidationHelper;
import no.niths.domain.location.Room;
import no.niths.domain.school.Student;
import no.niths.domain.school.Subject;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.infrastructure.location.interfaces.RoomRepository;
import no.niths.infrastructure.school.interfaces.StudentRepository;
import no.niths.infrastructure.school.interfaces.SubjectRepository;
import no.niths.services.AbstractGenericService;
import no.niths.services.school.interfaces.SubjectService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl extends AbstractGenericService<Subject>
		implements SubjectService {

    private Logger logger = LoggerFactory.getLogger(SubjectServiceImpl.class);

	@Autowired
	private SubjectRepository subjectRepository;

    @Autowired
	private StudentRepository studentRepository;

    @Autowired
	private RoomRepository roomRepository;

	public Subject getById(long id) {
		Subject s = subjectRepository.getById(id);
		if(s!= null){
			s.getTutors().size();
			if(s.getRoom() != null){
				s.getRoom().getRoomName();
			}
		}
		
		return s;
	}

	@Override
	public GenericRepository<Subject> getRepository() {
		return subjectRepository;
	}

    @Override
    public void addTutor(Long subjectId, Long studentId) {
        Subject subject = validate(subjectRepository.getById(subjectId), Subject.class);
        checkIfObjectIsInCollection(subject.getTutors(), studentId, Student.class);

        Student tutor = studentRepository.getById(studentId);
        ValidationHelper.isObjectNull(tutor, Student.class);

        subject.getTutors().add(tutor);
        logger.debug(MessageProvider.buildStatusMsg(Student.class,
                Status.UPDATED));
    }

    @Override
    public void removeTutor(Long subjectId, Long studentId) {
        Subject subject = validate(subjectRepository.getById(subjectId), Subject.class);
        checkIfIsRemoved(subject.getTutors().remove(new Student(studentId)),
                Student.class);
    }

    @Override
    public void addRoom(Long subjectId, Long roomId) {
        Subject subject = validate(subjectRepository.getById(subjectId), Subject.class);
        checkIfObjectExists(subject.getRoom(), roomId, Room.class);

        Room room = roomRepository.getById(roomId);
        ValidationHelper.isObjectNull(room, Room.class);

        subject.setRoom(room);
        logger.debug(MessageProvider.buildStatusMsg(Room.class,
                Status.UPDATED));
    }

    @Override
    public void removeRoom(Long subjectId) {
        Subject subject = validate(subjectRepository.getById(subjectId), Subject.class);

        boolean isRemoved = false;

        if (subject.getRoom() != null) {
            subject.setRoom(null);
            isRemoved = true;
        }

        checkIfIsRemoved(isRemoved, Room.class);
    }
}