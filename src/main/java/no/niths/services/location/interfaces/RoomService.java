package no.niths.services.location.interfaces;

import no.niths.domain.location.Room;
import no.niths.domain.signaling.AccessField;
import no.niths.services.interfaces.GenericService;

public interface RoomService extends GenericService<Room> {

    /**
     * 
     * @param roomId the room's id
     * @param accessFieldId the access field's id which will be added
     */
    void addAccessField(long roomId, long accessFieldId);

    /**
     * 
     * @param roomId the room's id
     * @param accessFieldId the access field's id which will be removed
     */
    void removeAccessField(long roomId, long accessFieldId);

    /**
     * 
     * @param accessField the access point of which the room belongs to
     * @return the Room with the given access point
     */
    Room getRoom(AccessField accessField);
}