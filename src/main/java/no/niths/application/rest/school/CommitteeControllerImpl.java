package no.niths.application.rest.school;

import javax.servlet.http.HttpServletResponse;

import no.niths.application.rest.AbstractRESTControllerImpl;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.lists.school.CommitteeList;
import no.niths.application.rest.school.interfaces.CommitteeController;
import no.niths.common.constants.DomainConstantNames;
import no.niths.common.constants.SecurityConstants;
import no.niths.domain.school.Committee;
import no.niths.services.interfaces.GenericService;
import no.niths.services.school.interfaces.CommitteeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for committee
 * has the basic CRUD methods and
 * methods too add and remove leader
 * and event
 *
 * For the URL too get Committee add /committees
 * after the {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}
 */
@Controller
@RequestMapping(DomainConstantNames.COMMITTEES)
public class CommitteeControllerImpl extends
        AbstractRESTControllerImpl<Committee> implements CommitteeController {

    @Autowired
    private CommitteeService committeeService;

    private CommitteeList committeeList = new CommitteeList();

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_SR_COMMITTEE_LEADER)
    public void delete(@PathVariable long id) {
        super.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_SR_COMMITTEE_LEADER)
    public Committee create(@RequestBody Committee domain, HttpServletResponse res) {
        return super.create(domain, res);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_SR_COMMITTEE_LEADER)
    public void update(@RequestBody Committee domain) {
        super.update(domain);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_SR_COMMITTEE_LEADER)
    @RequestMapping(value = "{committeeId}/leader/{studentId}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK, reason = "Leader added to committee")
    public void addLeader(@PathVariable Long committeeId,
            @PathVariable Long studentId) {
        committeeService.addLeader(committeeId, studentId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_SR_COMMITTEE_LEADER)
    @RequestMapping(value = "{committeeId}/leader/{studentId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK, reason = "Deleted")
    public void removeLeader(@PathVariable Long committeeId,
            @PathVariable Long studentId) {
        committeeService.removeLeader(committeeId, studentId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_SR_COMMITTEE_LEADER)
    @RequestMapping(value = "{committeeId}/event/{eventId}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK, reason = "Event added")
    public void addEvent(@PathVariable Long committeeId,
            @PathVariable Long eventId) {
        committeeService.addEvent(committeeId, eventId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_SR_COMMITTEE_LEADER)
    @RequestMapping(value = "{committeeId}/event/{eventId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK, reason = "Event removed")
    public void removeEvent(@PathVariable Long committeeId,
            @PathVariable Long eventId) {
        committeeService.removeEvent(committeeId, eventId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GenericService<Committee> getService() {
        return committeeService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListAdapter<Committee> getList() {
        return committeeList;
    }

}
