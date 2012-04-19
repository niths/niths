package no.niths.services.location;

import java.util.ArrayList;
import java.util.List;

import no.niths.application.rest.exception.ObjectInCollectionException;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.helper.Error;
import no.niths.application.rest.helper.Status;
import no.niths.common.LazyFixer;
import no.niths.common.MessageProvider;
import no.niths.common.ValidationHelper;
import no.niths.domain.location.Room;
import no.niths.domain.signaling.AccessField;
import no.niths.domain.signaling.AccessPoint;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.infrastructure.location.interfaces.RoomRepository;
import no.niths.infrastructure.signaling.interfaces.AccessFieldRepository;
import no.niths.services.AbstractGenericService;
import no.niths.services.location.interfaces.RoomService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public void addAccessField(long roomId, long accessFieldId) {
        Room room = repo.getById(roomId);
        ValidationHelper.isObjectNull(room, Room.class);
        room.getAccessFields().size();

        for (AccessField a : room.getAccessFields()) {
            if (a.getId() == accessFieldId) {
                throw new ObjectInCollectionException(
                        MessageProvider.buildErrorMsg(AccessField.class,
                                Error.OBJECT_IN_COLLECTION));
            }
        }

        AccessField af = afRepo.getById(accessFieldId);
        ValidationHelper.isObjectNull(af, AccessField.class);

        room.getAccessFields().add(af);
        logger.debug(MessageProvider.buildStatusMsg(Room.class, Status.UPDATED));
    }

    @Override
    public void removeAccessField(long roomId, long accessFieldId) {
        Room room = getById(roomId);
        ValidationHelper.isObjectNull(room, Room.class);

        room.getAccessFields().size();

        boolean isRemoved = false;
        for (AccessField af: room.getAccessFields()) {
            if (af.getId() == accessFieldId) {
                room.getAccessFields().remove(af);
                isRemoved = true;
                break;
            }
        }

        if (!isRemoved) {
            final String message = "Access field not found";
            logger.error(message);
            throw new ObjectNotFoundException(message);
        }
    }

    @Override
    public GenericRepository<Room> getRepository() {
        return repo;
    }

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