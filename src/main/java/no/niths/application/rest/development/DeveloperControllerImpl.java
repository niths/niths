package no.niths.application.rest.development;

import javax.servlet.http.HttpServletResponse;

import no.niths.application.rest.AbstractRESTControllerImpl;
import no.niths.application.rest.development.interfaces.DeveloperController;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.lists.development.DeveloperList;
import no.niths.common.constants.DomainConstantNames;
import no.niths.common.constants.SecurityConstants;
import no.niths.domain.development.Developer;
import no.niths.services.auth.interfaces.KeyGeneratorService;
import no.niths.services.development.interfaces.DeveloperService;
import no.niths.services.interfaces.GenericService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for developer
 * has the basic CRUD methods and
 * methods too add and remove application
 * and enable and disable developer
 * in addition too method for resetDeveloperKey,
 *
 * For the URL too get Developer add /developers
 * after the {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}
 */
@Controller
@RequestMapping(DomainConstantNames.DEVELOPERS)
public class DeveloperControllerImpl extends
        AbstractRESTControllerImpl<Developer> implements DeveloperController {

    @Autowired
    private DeveloperService service;
    
    @Autowired
    private KeyGeneratorService keyService;
    
    private DeveloperList developerList = new DeveloperList();

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR)
    @RequestMapping(method = RequestMethod.POST)
    public Developer create(@RequestBody Developer domain, HttpServletResponse res) {
        return super.create(domain, res);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR + " or (principal.developerId == #domain.id)")
    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        super.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR  + " or (principal.developerId == #domain.id)")
    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody Developer domain) {
        super.update(domain);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR)
    @RequestMapping(value = { "{developerId}/enable" }, method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Developer enabled")
    public void enableDeveloper(@PathVariable Long developerId){
        service.enableDeveloper(developerId);
    }


    /**
     * {@inheritDoc}
     */
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR)
    @RequestMapping(value = { "{developerId}/disable" }, method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Developer diabled")
    public void disableDeveloper(@PathVariable Long developerId){
        service.disableDeveloper(developerId);
    }
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR + " or (principal.developerId == #developerId)")
    @RequestMapping(value = { "{developerId}/resetDeveloperKey" }, method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Application added to developer")
    public void resetDeveloperKey(@PathVariable Long developerId){
        service.resetDeveloperKey(developerId,keyService.generateDeveloperKey());        
    }
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR + " or ((principal.developerId == #developerId) and (principal.appId == #applicationId))")
    @RequestMapping(value = { "{developerId}/application/{applicationId}" }, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK, reason = "Application added to developer")
    public void addApplication(@PathVariable Long developerId,@PathVariable Long applicationId) {
        service.addApplication(developerId,applicationId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR + " or ((principal.developerId == #developerId) and (principal.appId == #applicationId))")
    @RequestMapping(value = { "{developerId}/application/{applicationId}" }, method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK, reason = "Application removed from developer")
    public void removeApplication(@PathVariable Long developerId,@PathVariable Long applicationId) {
        service.removeApplicaiton(developerId,applicationId);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public GenericService<Developer> getService() {
        return service;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListAdapter<Developer> getList() {
        return developerList;
    }

}
