package no.niths.application.rest.school;

import javax.servlet.http.HttpServletResponse;

import no.niths.application.rest.AbstractRESTControllerImpl;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.lists.school.LockerList;
import no.niths.application.rest.school.interfaces.LockerController;
import no.niths.common.constants.DomainConstantNames;
import no.niths.common.constants.SecurityConstants;
import no.niths.domain.school.Locker;
import no.niths.services.interfaces.GenericService;
import no.niths.services.school.interfaces.LockerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Controller for locker
 * has the basic CRUD methods
 *
 * For the URL too get Locker add /lockers
 * after the {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}
 */
@Controller
@RequestMapping(DomainConstantNames.LOCKERS)
public class LockerControllerImpl extends AbstractRESTControllerImpl<Locker>
        implements LockerController {

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

    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR)
    public void delete(@PathVariable long id) {
        super.delete(id);
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