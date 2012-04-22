package no.niths.services.school;

import no.niths.application.rest.helper.Status;
import no.niths.common.helpers.MessageProvider;
import no.niths.common.helpers.ValidationHelper;
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
        Exam exam = validate(examRepository.getById(examId), Exam.class);
        checkIfObjectIsInCollection(exam.getRooms(), roomId, Room.class);

        Room room = roomRepository.getById(roomId);
        ValidationHelper.isObjectNull(room, Room.class);

        exam.getRooms().add(room);
        logger.debug(MessageProvider.buildStatusMsg(Room.class,
                Status.UPDATED));
    }

    @Override
    public void removeRoom(Long examId, Long roomId) {
        Exam exam = validate(examRepository.getById(examId),
                Exam.class);
        checkIfIsRemoved(exam.getRooms().remove(new Room(roomId)),
                Room.class);
    }

    @Override
    public void addSubject(Long examId, Long subjectId) {
        Exam exam = validate(examRepository.getById(examId), Exam.class);
        checkIfObjectExists(exam.getSubject(), subjectId, Subject.class);

        Subject subject = subjectRepository.getById(subjectId);
        ValidationHelper.isObjectNull(subject, Subject.class);

        exam.setSubject(subject);
        logger.debug(MessageProvider.buildStatusMsg(Subject.class,
                Status.UPDATED));
    }

    @Override
    public void removeSubject(Long examId) {
        Exam exam = validate(examRepository.getById(examId), Exam.class);

        boolean isRemoved = false;

        if (exam.getSubject() != null) {
            exam.setSubject(null);
            isRemoved = true;
        }

        checkIfIsRemoved(isRemoved, Subject.class);
    }
}