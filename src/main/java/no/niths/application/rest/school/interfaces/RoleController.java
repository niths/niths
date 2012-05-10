package no.niths.application.rest.school.interfaces;

import no.niths.application.rest.interfaces.GenericRESTController;
import no.niths.domain.school.Role;
/**
 * Controller for handling roles
 * has the basic CRUD methods
 *
 * For the URL too get Roles add /roles
 * after the {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}
 */
public interface RoleController extends GenericRESTController<Role> {
    
}
