package no.niths.services;

import java.util.List;

import no.niths.domain.Room;
import no.niths.infrastructure.interfaces.RoomRepository;
import no.niths.services.interfaces.RoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository repo;

    @Override
    public Long create(Room domain) {
        return null;
    }

    @Override
    public List<Room> getAll(Room domain) {
        return repo.getAll(domain);
    }

    @Override
    public Room getById(long id) {
        return repo.getById(id);
    }

    @Override
    public void update(Room domain) {
    	repo.update(domain);
    }

    @Override
    public boolean delete(long id) {
        return repo.delete(id);
    }

    @Override
    public void hibernateDelete(long id) {
        repo.hibernateDelete(id);
    }

}
