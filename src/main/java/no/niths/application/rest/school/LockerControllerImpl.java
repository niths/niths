package no.niths.application.rest.school;

import javax.servlet.http.HttpServletResponse;

import no.niths.application.rest.AbstractRESTControllerImpl;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.lists.LockerList;
import no.niths.application.rest.school.interfaces.LockerController;
import no.niths.common.AppConstants;
import no.niths.common.SecurityConstants;
import no.niths.domain.school.Locker;
import no.niths.services.interfaces.GenericService;
import no.niths.services.school.interfaces.LockerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(AppConstants.LOCKERS)
public class LockerControllerImpl extends AbstractRESTControllerImpl<Locker>
        implements LockerController {

    //private final Logger logger = LoggerFactory.getLogger(Locker.class);

    @Autowired
    private LockerService service;

    private LockerList lockerList = new LockerList();

    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR)
    public void create(@RequestBody Locker locker, HttpServletResponse res) {
        super.create(locker, res);
        
    }

    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR)
    public void update(@RequestBody Locker locker) {
        super.update(locker);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GenericService<Locker> getService() {
        return service;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListAdapter<Locker> getList() {
        return lockerList;
    }
}