package no.niths.infrastructure;

import no.niths.domain.location.Room;
import no.niths.infrastructure.interfaces.RoomRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class RoomRepositoryImpl extends AbstractGenericRepositoryImpl<Room> implements
		RoomRepository {

	private Logger logger = LoggerFactory.getLogger(RoomRepositoryImpl.class);

	public RoomRepositoryImpl() {
		super(Room.class, new Room());
	}
}