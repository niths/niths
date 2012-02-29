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

	@Override
	public void hibernateDelete(long id) {
		Role r = new Role();
		r.setId(id);
		getSession().getCurrentSession().delete(r);
	}
}
