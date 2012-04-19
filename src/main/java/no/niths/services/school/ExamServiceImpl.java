package no.niths.services.school;

import no.niths.application.rest.exception.ObjectInCollectionException;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.common.ValidationHelper;
import no.niths.domain.location.Room;
import no.niths.domain.school.Exam;
import no.niths.domain.school.Subject;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.infrastructure.location.interfaces.RoomRepository;
import no.niths.infrastructure.school.interfaces.ExamRepository;
import no.niths.infrastructure.school.interfaces.SubjectRepository;
import no.niths.services.AbstractGenericService;
import no.niths.services.school.interfaces.ExamService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExamServiceImpl extends AbstractGenericService<Exam> implements
		ExamService {

    private Logger logger = LoggerFactory.getLogger(ExamServiceImpl.class);

	@Autowired
	private ExamRepository examRepository;

    @Autowired
	private RoomRepository roomRepository;

    @Autowired
	private SubjectRepository subjectRepository;

	@Override
	public GenericRepository<Exam> getRepository() {
		return examRepository;
	}

    @Override
    public void addRoom(Long examId, Long roomId) {
        Exam exam = super.getById(examId);
        ValidationHelper.isObjectNull(exam, Exam.class);

        Room room = roomRepository.getById(roomId);
        ValidationHelper.isObjectNull(room, Room.class);

        if (!exam.getRooms().contains(room)) {
            exam.getRooms().add(room);
            logger.debug("Exam updated");
        } else {
            throw new ObjectInCollectionException(
                    "Room is already added to the exam");
        }
    }

    @Override
    public void removeRoom(Long examId, Long roomId) {
        Exam exam = super.getById(examId);
        ValidationHelper.isObjectNull(exam, Exam.class);

        boolean isRemoved = false;

        for (int i = 0; i < exam.getRooms().size(); i++) {
            if (exam.getRooms().get(i).getId() == roomId) {
                exam.getRooms().remove(i);
                isRemoved = true;
                break;
            }
        }

        if (isRemoved) {
            logger.debug("Room removed from exam " + exam.getName());
        } else {
            throw new ObjectNotFoundException("Room not found in exam");
        }
    }

    @Override
    public void addSubject(Long examId, Long subjectId) {
        Exam exam = super.getById(examId);
        ValidationHelper.isObjectNull(exam, Exam.class);

        Subject subject = subjectRepository.getById(subjectId);
        ValidationHelper.isObjectNull(subject, Subject.class);

        if (exam.getSubject() == null) {
            exam.setSubject(subject);
            logger.debug("Exam updated");
        } else {
            throw new ObjectInCollectionException(
                    "Exam already has a subject");
        }
    }

    @Override
    public void removeSubject(Long examId) {
        Exam exam = super.getById(examId);
        ValidationHelper.isObjectNull(exam, Exam.class);

        if (exam.getSubject() != null) {
            exam.setSubject(null);
        } else {
            logger.debug("Subject not found");
            throw new ObjectNotFoundException("Subject not found in exam");
        }
    }
}