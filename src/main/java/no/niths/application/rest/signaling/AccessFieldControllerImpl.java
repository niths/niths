package no.niths.application.rest.signaling;

import javax.servlet.http.HttpServletResponse;

import no.niths.aop.ApiEvent;
import no.niths.application.rest.AbstractRESTControllerImpl;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.lists.signaling.AccessFieldList;
import no.niths.application.rest.signaling.interfaces.AccessFieldController;
import no.niths.common.constants.DomainConstantNames;
import no.niths.common.constants.SecurityConstants;
import no.niths.domain.signaling.AccessField;
import no.niths.services.interfaces.GenericService;
import no.niths.services.signaling.interfaces.AccessFieldService;

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
 * has the basic CRUD methods and
 * methods too add and remove an accessPoint
 *
 * For the URL too get Access fields add /accessfields
 * after the {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}
 */
@Controller
@RequestMapping(DomainConstantNames.ACCESS_FIELDS)
public class AccessFieldControllerImpl extends
		AbstractRESTControllerImpl<AccessField> implements
		AccessFieldController {

	Logger logger = org.slf4j.LoggerFactory
			.getLogger(AccessFieldControllerImpl.class);

	@Autowired
	private AccessFieldService service;

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
	@ApiEvent(title = "Access field created")
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	public void create(@RequestBody AccessField domain, HttpServletResponse res) {
		super.create(domain, res);
	}

	@Override
	@ApiEvent(title = "Access field updated")
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	public void update(@RequestBody AccessField domain) {
		super.update(domain);
	}

	@Override
	@ApiEvent(title = "Access field deleted")
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	public void delete(@PathVariable long id) {
		super.delete(id);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@ApiEvent(title = "Access point added to access field")
	@RequestMapping(value = "{afId}/accesspoint/{apId}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK, reason = "AcessPoint Added")
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	public void addAccessPoint(@PathVariable long afId, @PathVariable long apId) {
		service.addAccessPoint(afId, apId);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@ApiEvent(title = "Access point removed from access field")
	@RequestMapping(value = "{afId}/accesspoint", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK, reason = "AcessPoint Removed")
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	public void removeAccessPoint(@PathVariable long afId) {
		service.removeAccessPoint(afId);
	}
}