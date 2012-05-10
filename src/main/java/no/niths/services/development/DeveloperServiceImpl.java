package no.niths.services.development;

import java.util.List;

import no.niths.application.rest.helper.Status;
import no.niths.common.helpers.MessageProvider;
import no.niths.common.helpers.ValidationHelper;
import no.niths.domain.development.Application;
import no.niths.domain.development.Developer;
import no.niths.domain.school.Committee;
import no.niths.infrastructure.development.interfaces.ApplicationRepository;
import no.niths.infrastructure.development.interfaces.DeveloperRepository;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.services.AbstractGenericService;
import no.niths.services.development.interfaces.DeveloperService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Service Class for Developer
 *
 * <p>
 * Inherits the basic CRUD actions and has methods
 * for getAllWithApps, getDeveloperByDeveloperKey,
 * enableDeveloper, disableDeveloper,
 * resetDeveloperKey, addApplication
 * and removeApplicaiton
 * </p>
 */
@Service
public class DeveloperServiceImpl extends AbstractGenericService<Developer>
        implements DeveloperService {

    @Autowired
    private DeveloperRepository repo;

    @Autowired
    private ApplicationRepository applicationRepo;

    private Logger logger = LoggerFactory.getLogger(DeveloperServiceImpl.class);

    /**
     * {@inheritDoc}
     */
    @Override
    @Deprecated
    public Developer getDeveloperByDeveloperToken(String token,
            boolean isEnabled) {
        return repo.getByDeveloperToken(token, isEnabled);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Developer getDeveloperByDeveloperKey(String key) {
        Developer dev = repo.getByDeveloperKey(key);
        if (dev != null) {
            dev.getApps().size();
        }
        return dev;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Developer> getAllWithApps(Developer dev) {
        List<Developer> all = repo.getAll(dev);
        for (Developer d : all) {
            d.getApps().size();
        }
        return all;
    }

    @Override
    public GenericRepository<Developer> getRepository() {
        return repo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enableDeveloper(Long developerId) {
        Developer developer = validate(repo.getById(developerId),
                Developer.class);
        developer.setEnabled(true);
        logger.debug("Developer " + developer.getName() + " is enabled");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disableDeveloper(Long developerId) {
        Developer developer = validate(repo.getById(developerId),
                Developer.class);
        developer.setEnabled(false);
        logger.debug("Developer " + developer.getName() + " is disabled");

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetDeveloperKey(Long developerId, String generatedDeveloperKey) {
        Developer developer = validate(repo.getById(developerId),
                Developer.class);
        logger.debug("OLD-dev key :" + developer.getDeveloperKey()
                + "\n new developer key :" + generatedDeveloperKey);
        developer.setDeveloperKey(generatedDeveloperKey);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addApplication(Long developerId, Long applicationId) {
        Developer developer = validate(repo.getById(developerId),
                Developer.class);
        checkIfObjectIsInCollection(developer.getApps(), applicationId,
                Application.class);

        Application app = applicationRepo.getById(applicationId);
        ValidationHelper.isObjectNull(app, Application.class);

        developer.getApps().add(app);
        logger.debug(MessageProvider.buildStatusMsg(Committee.class,
                Status.UPDATED));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeApplicaiton(Long developerId, Long applicationId) {
        Developer developer = validate(repo.getById(developerId),
                Developer.class);
        checkIfIsRemoved(
                developer.getApps().remove(new Application(applicationId)),
                Application.class);
    }
}