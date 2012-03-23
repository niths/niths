package no.niths.services;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import no.niths.domain.security.Role;
import no.niths.infrastructure.interfaces.RoleRepository;
import no.niths.services.interfaces.RoleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

	private Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
	private CustomBeanUtilsBean beanCopy = new CustomBeanUtilsBean();

    @Autowired
    private RoleRepository repo;

    public Long create(Role role) {
        return repo.create(role);
    }

    public Role getById(long id) {
    	return repo.getById(id);
   }

    public List<Role> getAll(Role role) {
    	return repo.getAll(role);
    }

    public void update(Role role) {
    	Role roleToUpdate = repo.getById(role.getId());
		try {
			beanCopy.copyProperties(roleToUpdate, role);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("error",e);
			e.printStackTrace();
		}
		repo.update(roleToUpdate);
    }

    public boolean delete(long id) {
        return repo.delete(id);
    }

	@Override
	public void hibernateDelete(long id) {
		repo.hibernateDelete(id);
	}
}