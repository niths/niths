package no.niths.infrastructure.location;

import no.niths.domain.location.Room;
import no.niths.infrastructure.AbstractGenericRepositoryImpl;
import no.niths.infrastructure.location.interfaces.RoomRepository;

import org.springframework.stereotype.Repository;
/**
 * Repository class for Room
 *
 * <p>
 * Inherits the basic CRUD actions
 * </p>
 */
@Repository
public class RoomRepositoryImpl extends AbstractGenericRepositoryImpl<Room> implements
		RoomRepository {

	public RoomRepositoryImpl() {
		super(Room.class, new Room());
	}
}