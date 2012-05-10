package no.niths.services.school;

import java.util.List;

import no.niths.application.rest.helper.Status;
import no.niths.common.helpers.MessageProvider;
import no.niths.common.helpers.ValidationHelper;
import no.niths.domain.school.Committee;
import no.niths.domain.school.Event;
import no.niths.domain.school.Feed;
import no.niths.domain.school.Role;
import no.niths.domain.school.Student;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.infrastructure.school.interfaces.CommitteeRepositorty;
import no.niths.infrastructure.school.interfaces.EventRepository;
import no.niths.infrastructure.school.interfaces.RoleRepository;
import no.niths.infrastructure.school.interfaces.StudentRepository;
import no.niths.services.AbstractGenericService;
import no.niths.services.school.interfaces.CommitteeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Service Class for Committee
 *
 * <p>
 * Inherits the basic CRUD actions and has methods
 * for addLeader, removeLeader,
 * addEvent and removeEvent
 * </p>
 */
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

    @Autowired
    private RoleRepository roleRepo;
    
    @Override
    public GenericRepository<Committee> getRepository() {
        return repo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addLeader(Long committeeId, Long studentId) {
        Committee committee = validate(repo.getById(committeeId),
                Committee.class);
        checkIfObjectIsInCollection(committee.getLeaders(), studentId,
                Student.class);

        Student student = studentRepo.getById(studentId);
        ValidationHelper.isObjectNull(student, Student.class);

        Role r = new Role();
        r.setRoleName("ROLE_COMMITTEE_LEADER");
        List<Role> roles = roleRepo.getAll(r);
        if (roles.size() > 0) {
            student.getRoles().add(roles.get(0));
        }
        
        
        committee.getLeaders().add(student);
        logger.debug(MessageProvider.buildStatusMsg(Feed.class, Status.UPDATED));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeLeader(Long committeeId, Long studentId) {
        Committee committee = validate(repo.getById(committeeId),
                Committee.class);
        Student student = studentRepo.getById(studentId);

        checkIfIsRemoved(committee.getLeaders().remove(student),
                Student.class);
                
        for(Role r:student.getRoles()){
            if(r.getRoleName().equals("ROLE_COMMITTEE_LEADER")){
                student.getRoles().remove(r);
                break;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeEvent(Long committeeId, Long eventId) {
        Committee committee = validate(repo.getById(committeeId),
                Committee.class);

        checkIfIsRemoved(committee.getEvents().remove(new Event(eventId)),
                Event.class);
    }

}
