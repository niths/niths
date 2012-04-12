package no.niths.application.rest;

import java.util.ArrayList;

import no.niths.aop.ApiEvent;
import no.niths.application.rest.exception.DuplicateEntryCollectionException;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.interfaces.AccessFieldController;
import no.niths.application.rest.lists.AccessFieldList;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.AppConstants;
import no.niths.common.SecurityConstants;
import no.niths.common.ValidationHelper;
import no.niths.domain.signaling.AccessField;
import no.niths.domain.signaling.AccessPoint;
import no.niths.services.interfaces.AccessFieldService;
import no.niths.services.interfaces.AccessPointService;
import no.niths.services.interfaces.GenericService;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller for AccessField
 */
@Controller
@RequestMapping(AppConstants.ACCESS_FIELDS)
public class AccessFieldControllerImpl extends
        AbstractRESTControllerImpl<AccessField> implements
        AccessFieldController {

    Logger logger = org.slf4j.LoggerFactory
            .getLogger(AccessFieldControllerImpl.class);

    @Autowired
    private AccessFieldService service;

    @Autowired
    private AccessPointService apService;

    private AccessFieldList list = new AccessFieldList();

    @Override
    public GenericService<AccessField> getService() {
        return service;
    }
    
    @Override
    public ListAdapter<AccessField> getList() {
        return list;
    }

    @Override
    public ArrayList<AccessField> getAll(AccessField domain) {
        super.getAll(domain);
        return list;
    }
    
    @Override
    public ArrayList<AccessField> getAll(
            AccessField domain,
            @PathVariable int firstResult,
            @PathVariable int maxResults) {
        super.getAll(domain,firstResult,maxResults);
        return list;
    }
    
    @Override
    @ApiEvent(title = "Access field created")
    @PreAuthorize(SecurityConstants.ONLY_ADMIN)
    public void create(@RequestBody AccessField domain) {
        super.create(domain);
    }

    @Override
    @ApiEvent(title = "Access field updated")
    @PreAuthorize(SecurityConstants.ONLY_ADMIN)
    public void update(@RequestBody AccessField domain) {
        super.update(domain);
    }

    @Override
    @ApiEvent(title = "Access field deleted")
    @PreAuthorize(SecurityConstants.ONLY_ADMIN)
    public void hibernateDelete(@PathVariable long id) {
        super.hibernateDelete(id);
    }

    @Override
    @ApiEvent(title = "Access point added to access field")
    @RequestMapping(value = "add/accesspoint/{afId}/{apId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "AcessPoint Added")
    public void addAccessPoint(@PathVariable long afId, @PathVariable long apId) {
        AccessField af = service.getById(afId);
        ValidationHelper.isObjectNull(af, "Acess field does not exist");
        if (af.getAccessPoint() != null && af.getAccessPoint().getId() == apId) {
            logger.debug("Acess Point exist");
            throw new DuplicateEntryCollectionException("Acess Point exist");
        }
        
        AccessPoint ap = apService.getById(apId);
        ValidationHelper.isObjectNull(ap, "Access point does not exist");
        af.setAccessPoint(ap);
        service.update(af);
        logger.debug("Accss field updated and changed");

    }

    @Override
    @ApiEvent(title = "Access point removed from access field")
    @RequestMapping(value = "remove/accesspoint/{afId}/{apId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Acesspoint Removed")
    public void removeAccessPoint(@PathVariable long afId,
            @PathVariable long apId) {
        AccessField af = service.getById(afId);
        ValidationHelper.isObjectNull(af, "Room does not exist");

        boolean isRemoved = false;
        if (af.getAccessPoint() != null && af.getAccessPoint().getId() == apId) {
            isRemoved = true;
            af.setAccessPoint(null);
        }

        if (isRemoved) {
            service.update(af);
        } else {
            logger.debug("Access point not Found");
            throw new ObjectNotFoundException("Access point not Found");
        }

    }
}