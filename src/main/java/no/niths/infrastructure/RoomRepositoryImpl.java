package no.niths.infrastructure;

import no.niths.domain.Room;
import no.niths.infrastructure.interfaces.RoomRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class RoomRepositoryImpl extends GenericRepositoryImpl<Room> implements
		RoomRepository {

	private Logger logger = LoggerFactory.getLogger(RoomRepositoryImpl.class);

	public RoomRepositoryImpl() {
		super(Room.class);
	}

	@Override
	public void hibernateDelete(long id) {
		Room room = new Room();
		room.setId(id);
		getSession().getCurrentSession().delete(room);
	}
}