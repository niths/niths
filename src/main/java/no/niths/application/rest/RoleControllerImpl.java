package no.niths.application.rest;

import no.niths.application.rest.interfaces.RoleController;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.lists.RoleList;
import no.niths.common.AppConstants;
import no.niths.domain.security.Role;
import no.niths.services.interfaces.GenericService;
import no.niths.services.interfaces.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for handling roles
 * 
 */
@Controller
@RequestMapping(AppConstants.ROLES)
public class RoleControllerImpl extends AbstractRESTControllerImpl<Role>
		implements RoleController {

	@Autowired
	private RoleService roleService;

	private RoleList roleList = new RoleList();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GenericService<Role> getService() {
		return roleService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListAdapter<Role> getList() {
		return roleList;
	}
}
