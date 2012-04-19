package no.niths.services.school;

import java.util.ArrayList;
import java.util.List;

import no.niths.application.rest.exception.ObjectInCollectionException;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.common.ValidationHelper;
import no.niths.domain.school.FadderGroup;
import no.niths.domain.school.Student;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.infrastructure.school.interfaces.FadderGroupRepository;
import no.niths.infrastructure.school.interfaces.StudentRepository;
import no.niths.services.AbstractGenericService;
import no.niths.services.school.interfaces.FadderGroupService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FadderGroupServiceImpl extends AbstractGenericService<FadderGroup>
		implements FadderGroupService {

    private Logger logger = LoggerFactory.getLogger(FadderGroupServiceImpl.class);

	@Autowired
	private FadderGroupRepository fadderGroupRepository;

    @Autowired
    private StudentRepository studentRepository;

	@Override
	public GenericRepository<FadderGroup> getRepository() {
		return fadderGroupRepository;
	}

	@Override
	public List<FadderGroup> getAll(FadderGroup domain) {
		List<FadderGroup> list = fadderGroupRepository.getAll(domain);
		for (FadderGroup fg : list) {
			fg.getLeaders().size();
		}
		return list;
	}

    @Override
    public void addLeader(Long groupId, Long studentId) {
        FadderGroup group = super.getById(groupId);
        ValidationHelper.isObjectNull(group, FadderGroup.class);

        Student leader = studentRepository.getById(studentId);
        ValidationHelper.isObjectNull(leader, Student.class);

        if (!group.getLeaders().contains(leader)) {
            group.getLeaders().add(leader);
            logger.debug("Fadder group updated");
        } else {
            throw new ObjectInCollectionException(
                    "Leader is already added to the fadder group");
        }
    }

    @Override
    public void removeLeader(Long groupId, Long studentId) {
        FadderGroup group = super.getById(groupId);
        ValidationHelper.isObjectNull(group, FadderGroup.class);

        Student leader = studentRepository.getById(studentId);
        ValidationHelper.isObjectNull(leader, Student.class);

        boolean isRemoved = false;

        for (int i = 0; i < group.getLeaders().size(); i++) {
            if (group.getLeaders().get(i).getId() == studentId) {
                group.getLeaders().remove(i);
                isRemoved = true;
            }
        }

        if (isRemoved) {
            logger.debug("Leader removed from fadder group");
        } else {
            logger.debug("Leader not found");
            throw new ObjectNotFoundException("Leader not found in fadder group");
        }
    }

    @Override
    public void addChild(Long groupId, Long studentId) {
        FadderGroup group = super.getById(groupId);
        ValidationHelper.isObjectNull(group, FadderGroup.class);

        Student child = studentRepository.getById(studentId);
        ValidationHelper.isObjectNull(child, Student.class);

        if (!group.getFadderChildren().contains(child)) {
            group.getFadderChildren().add(child);
            logger.debug("Fadder group updated");
        } else {
            throw new ObjectInCollectionException(
                    "Child is already added to the fadder group");
        }
    }

    @Override
    public void removeChild(Long groupId, Long studentId) {
        FadderGroup group = super.getById(groupId);
        ValidationHelper.isObjectNull(group, FadderGroup.class);

        Student child = studentRepository.getById(studentId);
        ValidationHelper.isObjectNull(child, Student.class);

        boolean isRemoved = false;

        for (int i = 0; i < group.getFadderChildren().size(); i++) {
            if (group.getFadderChildren().get(i).getId() == studentId) {
                group.getFadderChildren().remove(i);
                isRemoved = true;
            }
        }

        if (isRemoved) {
            logger.debug("Child removed from fadder group");
        } else {
            logger.debug("Child not found");
            throw new ObjectNotFoundException("Child not found in fadder group");
        }
    }

    @Override
    public void removeChildren(Long groupId, Long[] studentIds) {
        for (Long studentId : studentIds) {
            removeChild(groupId, studentId);
        }
    }

    @Override
    public void removeAllChildren(Long groupId) {
        FadderGroup group = super.getById(groupId);

        if(!group.getFadderChildren().isEmpty()){
            group.setFadderChildren(null);
        }else{
            logger.debug("List was empty no need for update");
            throw new ObjectNotFoundException(
                    "List was empty no need for update");
        }
    }

    @Override
    public void removeAllLeaders(Long groupId) {
        FadderGroup group = super.getById(groupId);

        if(!group.getLeaders().isEmpty()){
            group.setLeaders(null);
        } else {
            logger.debug("List was empty no need for update");
            throw new ObjectNotFoundException(
                    "List was empty no need for update");
        }
    }
    
    /**
     * Returns all students without a fadder group
     * @return list with students
     */
    @Override
    public List<Student> getStudentsNotInAGroup(){
    	return fadderGroupRepository.getStudentsNotInAGroup();
    }
}