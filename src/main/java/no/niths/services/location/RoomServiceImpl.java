package no.niths.services.location;

import java.util.ArrayList;
import java.util.List;

import no.niths.application.rest.helper.Status;
import no.niths.common.helpers.LazyFixer;
import no.niths.common.helpers.MessageProvider;
import no.niths.common.helpers.ValidationHelper;
import no.niths.domain.location.Room;
import no.niths.domain.signaling.AccessField;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.infrastructure.location.interfaces.RoomRepository;
import no.niths.infrastructure.signaling.interfaces.AccessFieldRepository;
import no.niths.services.AbstractGenericService;
import no.niths.services.location.interfaces.RoomService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Service Class for Room
 *
 * <p>
 * Inherits the basic CRUD actions and has methods
 * for addAccessField, removeAccessField,
 * and getRoom
 * </p>
 */
@Service
public class RoomServiceImpl extends AbstractGenericService<Room> implements
        RoomService {

    private Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class);

    LazyFixer<Room> lazyFixer = new LazyFixer<Room>();

    @Autowired
    private RoomRepository repo;

    @Autowired
    private AccessFieldRepository afRepo;

    @Override
    public List<Room> getAll(Room domain) {
        List<Room> rooms = repo.getAll(domain);
        lazyFixer.fetchChildren(rooms);
        return rooms;
    }

    @Override
    public Room getById(long id) {
        Room room = repo.getById(id);
        List<Room> roomList = new ArrayList<Room>();
        roomList.add(room);
        lazyFixer.fetchChildren(roomList);
        return roomList.get(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addAccessField(long roomId, long accessFieldId) {
        Room room = validate(repo.getById(roomId), Room.class);
        checkIfObjectIsInCollection(room.getAccessFields(), accessFieldId, AccessField.class);

        AccessField accessField = afRepo.getById(accessFieldId);
        ValidationHelper.isObjectNull(accessField, AccessField.class);

        room.getAccessFields().add(accessField);
        logger.debug(MessageProvider.buildStatusMsg(AccessField.class,
                Status.UPDATED));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeAccessField(long roomId, long accessFieldId) {
        Room room = validate(repo.getById(roomId), Room.class);
        checkIfIsRemoved(room.getAccessFields().remove(new AccessField(accessFieldId)),
                AccessField.class);
    }

    @Override
    public GenericRepository<Room> getRepository() {
        return repo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Room getRoom(AccessField accessField) {
        System.err.println("checking room.................................");
        List<Room> rooms = repo.getAll(null);

        for (Room room : rooms) {
            for (AccessField innerAccessField : room.getAccessFields()) {
                if (accessField.isWithinRanges(innerAccessField)) {
                    System.err.println("================");
                    System.err.println(room.getRoomName());
                    System.err.println("================");
                }
            }
        }

        return null;
    }
}