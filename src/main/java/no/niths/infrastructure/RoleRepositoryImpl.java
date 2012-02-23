package no.niths.infrastructure;

import no.niths.domain.security.Role;
import no.niths.infrastructure.interfaces.RoleRepository;

import org.springframework.stereotype.Repository;

@Repository
public class RoleRepositoryImpl extends GenericRepositoryImpl<Role>
		implements RoleRepository{

	public RoleRepositoryImpl() {
		super(Role.class);
	}

}
