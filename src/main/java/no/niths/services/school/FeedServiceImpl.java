package no.niths.services.school;

import no.niths.common.helpers.ValidationHelper;
import no.niths.domain.location.Location;
import no.niths.domain.school.Committee;
import no.niths.domain.school.Feed;
import no.niths.domain.school.Student;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.infrastructure.location.interfaces.LocationRepository;
import no.niths.infrastructure.school.interfaces.CommitteeRepositorty;
import no.niths.infrastructure.school.interfaces.FeedRepoistory;
import no.niths.infrastructure.school.interfaces.StudentRepository;
import no.niths.services.AbstractGenericService;
import no.niths.services.school.interfaces.FeedService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Service Class for Feed
 *
 * <p>
 * Inherits the basic CRUD actions and has methods
 * for addLocation, removeLocation,
 * addStudent, removeStudent,
 * addCommittee
 * and removeCommittee
 * </p>
 */
@Service
public class FeedServiceImpl extends AbstractGenericService<Feed> implements
        FeedService {

    @Autowired
    private FeedRepoistory repo;

    @Autowired
    private LocationRepository locationRepo;

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private CommitteeRepositorty committeeRepo;
    
    @Override
    public GenericRepository<Feed> getRepository() {
        return repo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addLocation(Long feedId, Long locationId) {
        Feed feed = validate(repo.getById(feedId), Feed.class);
        checkIfObjectExists(feed.getLocation(), locationId, Location.class);
        Location location = locationRepo.getById(locationId);
        ValidationHelper.isObjectNull(location, Location.class);
        feed.setLocation(location);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeLocation(Long feedId) {
        Feed feed = validate(repo.getById(feedId), Feed.class);

        boolean isRemoved = false;
        if (feed.getLocation() != null) {
            isRemoved = true;
            feed.setLocation(null);
        }

        checkIfIsRemoved(isRemoved, Location.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addStudent(Long feedId, Long studentId) {
        Feed feed = validate(repo.getById(feedId), Feed.class);
        checkIfObjectExists(feed.getStudent(), studentId, Student.class);
        Student student = studentRepo.getById(studentId);
        ValidationHelper.isObjectNull(student, Student.class);
        feed.setStudent(student);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeStudent(Long feedId) {
        Feed feed = validate(repo.getById(feedId), Feed.class);

        boolean isRemoved = false;
        if (feed.getStudent() != null) {
            isRemoved = true;
            feed.setStudent(null);
        }

        checkIfIsRemoved(isRemoved, Student.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeCommittee(Long feedId) {
        Feed feed = validate(repo.getById(feedId), Feed.class);

        boolean isRemoved = false;
        if (feed.getCommittee() != null) {
            isRemoved = true;
            feed.setCommittee(null);
        }

        checkIfIsRemoved(isRemoved, Committee.class);
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCommittee(Long feedId, Long committeeId) {
        Feed feed = validate(repo.getById(feedId), Feed.class);
        checkIfObjectExists(feed.getCommittee(), committeeId, Committee.class);
        Committee committee = committeeRepo.getById(committeeId);
        ValidationHelper.isObjectNull(committee, Committee.class);
        feed.setCommittee(committee);
    }

}
