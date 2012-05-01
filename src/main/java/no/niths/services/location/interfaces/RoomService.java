package no.niths.services.location.interfaces;

import no.niths.domain.location.Room;
import no.niths.domain.signaling.AccessField;
import no.niths.services.interfaces.GenericService;
/**
 * Service Class for Room
 *
 * <p>
 * Inherits the basic CRUD actions and has methods
 * for addAccessField, removeAccessField,
 * and getRoom
 * </p>
 */
public interface RoomService extends GenericService<Room> {

    /**
     * Adds a accessField to the room
     * @param roomId the room's id
     * @param accessFieldId the access field's id which will be added
     */
    void addAccessField(long roomId, long accessFieldId);

    /**
     * Removes a accessField to the room
     * @param roomId the room's id
     * @param accessFieldId the access field's id which will be removed
     */
    void removeAccessField(long roomId, long accessFieldId);

    /**
     * Returns the room that have the specific accessField
     * @param accessField the access point of which the room belongs to
     * @return the Room with the given access point
     */
    Room getRoom(AccessField accessField);
}