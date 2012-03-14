package no.niths.services;

import java.util.List;

import no.niths.domain.AccessPoint;
import no.niths.infrastructure.interfaces.AccessPointRepository;
import no.niths.services.interfaces.AccessPointService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccessPointServiceImpl implements AccessPointService {

    @Autowired
    private AccessPointRepository repo;

    @Override
    public Long create(AccessPoint domain) {
        return null;
    }

    @Override
    public List<AccessPoint> getAll(AccessPoint accessPoint) {
        return repo.getAll(accessPoint);
    }

    @Override
    public AccessPoint getById(long id) {
        return repo.getById(id);
    }

    @Override
    public void update(AccessPoint domain) {
        
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public void hibernateDelete(long id) {
        
    }
}