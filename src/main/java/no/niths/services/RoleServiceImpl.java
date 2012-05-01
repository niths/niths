package no.niths.services;

import no.niths.domain.security.Role;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.infrastructure.interfaces.RoleRepository;
import no.niths.services.interfaces.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Service Class for Role
 *
 * <p>
 * Inherits the basic CRUD actions
 * </p>
 */
@Service
public class RoleServiceImpl extends AbstractGenericService<Role> implements RoleService{

	
    @Autowired
    private RoleRepository repo;

	@Override
	public GenericRepository<Role> getRepository() {
		return repo;
	}
}