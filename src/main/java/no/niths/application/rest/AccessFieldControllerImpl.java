package no.niths.application.rest;

import no.niths.application.rest.interfaces.AccessFieldController;
import no.niths.application.rest.lists.AccessFieldList;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.AppConstants;
import no.niths.common.SecurityConstants;
import no.niths.common.ValidationHelper;
import no.niths.domain.signaling.AccessField;
import no.niths.services.interfaces.AccessFieldService;
import no.niths.services.interfaces.GenericService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for AccessField
 */
@Controller
@RequestMapping(AppConstants.ACCESS_FIELDS)
public class AccessFieldControllerImpl extends
		AbstractRESTControllerImpl<AccessField> implements
		AccessFieldController {

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
	@PreAuthorize(SecurityConstants.ONLY_ADMIN)
	public void create(AccessField domain) {
		super.create(domain);
	}

	@Override
	@PreAuthorize(SecurityConstants.ONLY_ADMIN)
	public void update(AccessField domain) {
		super.update(domain);

	}

	@Override
	@PreAuthorize(SecurityConstants.ONLY_ADMIN)
	public void hibernateDelete(long id) {
		super.hibernateDelete(id);

	}
}