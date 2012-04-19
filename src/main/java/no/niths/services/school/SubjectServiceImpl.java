package no.niths.services.school;

import no.niths.application.rest.exception.NotInCollectionException;
import no.niths.application.rest.exception.ObjectInCollectionException;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.common.ValidationHelper;
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
        Subject subject = super.getById(subjectId);
        ValidationHelper.isObjectNull(subject, Subject.class);

        Student student = studentRepository.getById(studentId);
        ValidationHelper.isObjectNull(student, Student.class);

        if (!subject.getTutors().contains(student)) {
            subject.getTutors().add(student);
            logger.debug("Subject updated");
        } else {
            throw new ObjectInCollectionException(
                    "Tutor is already added to the subject");
        }
    }

    @Override
    public void removeTutor(Long subjectId, Long studentId) {
        Subject subject = super.getById(subjectId);
        ValidationHelper.isObjectNull(subject, Subject.class);

        boolean isRemoved = false;

        for (int i = 0; i < subject.getTutors().size(); i++) {
            if (subject.getTutors().get(i).getId() == studentId) {
                subject.getTutors().remove(i);
                isRemoved = true;
            }
        }

        if (isRemoved) {
            logger.debug("Tutor removed from subject " + subject.getName());
        } else {
            throw new NotInCollectionException("Student is not a tutor");
        }
    }

    @Override
    public void addRoom(Long subjectId, Long roomId) {
        Subject subject = super.getById(subjectId);
        ValidationHelper.isObjectNull(subject, Subject.class);

        Room room = roomRepository.getById(roomId);
        ValidationHelper.isObjectNull(room, Room.class);

        if (subject.getRoom() == null) {
            subject.setRoom(room);
            logger.debug("Subject updated");
        } else {
            throw new ObjectInCollectionException(
                    "Room is already added to the subject");
        }
    }

    @Override
    public void removeRoom(Long subjectId) {
        Subject subject = super.getById(subjectId);
        ValidationHelper.isObjectNull(subject, Subject.class);

        if (subject.getRoom() != null) {
            subject.setRoom(null);
        } else {
            logger.debug("Room not found");
            throw new ObjectNotFoundException("Room not found in subject");
        }
    }
}