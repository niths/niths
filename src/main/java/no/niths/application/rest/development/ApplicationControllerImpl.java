package no.niths.application.rest.development;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import no.niths.application.rest.AbstractRESTControllerImpl;
import no.niths.application.rest.RESTConstants;
import no.niths.application.rest.development.interfaces.ApplicationController;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.lists.development.ApplicationList;
import no.niths.common.constants.DomainConstantNames;
import no.niths.common.constants.SecurityConstants;
import no.niths.domain.development.Application;
import no.niths.services.development.interfaces.ApplicationService;
import no.niths.services.interfaces.GenericService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller for application
 * has the basic CRUD methods and
 * methods too enable and disable application
 * in addition too method for getTopApps,
 *
 * For the URL too get Application add /applications
 * after the {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}
 */
@Controller
@RequestMapping(DomainConstantNames.APPLICATIONS)
public class ApplicationControllerImpl extends
        AbstractRESTControllerImpl<Application> implements
        ApplicationController {

    @Autowired
    private ApplicationService service;

    private ApplicationList applicationList = new ApplicationList();

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR)
    @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    public Application create(
            @RequestBody Application domain,
            HttpServletResponse res) {
        return super.create(domain, res);
    }

    /**
     * Deletes an application
     * <p>
     * Developers must log in to be able to delete their applications
     * </p>
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR + " or (principal.appId == #id)")
    public void delete(@PathVariable long id) {
        super.delete(id);
    }

    /**
     * Updates an application
     * <p>
     * Developers must log in to be able to update their applications
     * </p>
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR+ " or (principal.appId == #domain.id)")
    public void update(@RequestBody Application domain) {
        super.update(domain);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR)
    @RequestMapping(value = { "{applicationId}/enable" }, method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Application enabled")
    public void enableApplication(@PathVariable Long applicationId) {
        service.enableApplication(applicationId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR)
    @RequestMapping(value = { "{applicationId}/disable" }, method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Application disable")
    public void disableApplication(@PathVariable Long applicationId){
        service.disableApplication(applicationId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = {"top/{maxResults}"}, method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
    @ResponseBody
    public List<Application> getTopApps(@PathVariable int maxResults){
        renewList(service.getTopApps(maxResults));
        return applicationList;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public GenericService<Application> getService() {
        return service;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListAdapter<Application> getList() {
        return applicationList;
    }

}
