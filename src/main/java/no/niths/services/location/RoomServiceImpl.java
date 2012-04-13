package no.niths.services.location;

import java.util.List;

import no.niths.domain.location.Room;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.infrastructure.interfaces.RoomRepository;
import no.niths.services.AbstractGenericService;
import no.niths.services.location.interfaces.RoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl extends AbstractGenericService<Room> implements RoomService {


	@Autowired
	private RoomRepository repo;

	@Override
	public List<Room> getAll(Room domain) {
		List<Room> rooms = repo.getAll(domain);
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
	public GenericRepository<Room> getRepository() {
		return repo;
	}

}
