package no.niths.services;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import no.niths.domain.location.Room;
import no.niths.infrastructure.interfaces.RoomRepository;
import no.niths.services.interfaces.RoomService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

	private Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class);
	private CustomBeanUtilsBean beanCopy = new CustomBeanUtilsBean();

	@Autowired
	private RoomRepository repo;

	@Override
	public Long create(Room domain) {
		return repo.create(domain);
	}

	@Override
	public List<Room> getAll(Room domain) {
		List<Room> rooms = repo.getAll(null);
		for (Room r : rooms) {
			r.getAccessFields().size();
		}
		return rooms;
	}

	@Override
	public Room getById(long id) {
		Room room = repo.getById(id);
		if (room != null) {
			room.getAccessFields().size();
		}
		return room;
	}

	@Override
	public void update(Room room) {
		Room roomToUpdate = repo.getById(room.getId());
		try {
			beanCopy.copyProperties(roomToUpdate, room);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("error",e);
			e.printStackTrace();
		}
		repo.update(roomToUpdate);
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
