package no.niths.application.rest.school;

import no.niths.application.rest.AbstractRESTControllerImpl;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.lists.school.RoleList;
import no.niths.application.rest.school.interfaces.RoleController;
import no.niths.common.constants.DomainConstantNames;
import no.niths.domain.school.Role;
import no.niths.services.interfaces.GenericService;
import no.niths.services.school.interfaces.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for handling roles
 * has the basic CRUD methods
 *
 * For the URL too get Roles add /roles
 * after the {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}
 */
@Controller
@RequestMapping(DomainConstantNames.ROLES)
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
