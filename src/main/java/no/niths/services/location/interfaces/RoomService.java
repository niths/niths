package no.niths.services.location.interfaces;

import no.niths.domain.location.Room;
import no.niths.services.interfaces.GenericService;

public interface RoomService extends GenericService<Room> {

    /**
     * 
     * @param room the room
     * @param accessFieldId the access field id which will be removed
     */
    void removeAccessField(long roomId, long accessFieldId);
}