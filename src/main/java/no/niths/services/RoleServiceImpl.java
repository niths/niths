package no.niths.services;

import java.util.List;

import no.niths.domain.Subject;
import no.niths.domain.security.Role;
import no.niths.infrastructure.interfaces.RoleRepository;
import no.niths.infrastructure.interfaces.SubjectRepository;
import no.niths.services.interfaces.RoleService;
import no.niths.services.interfaces.SubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

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
        repo.update(role);
    }

    public boolean delete(long id) {
        return repo.delete(id);
    }
}