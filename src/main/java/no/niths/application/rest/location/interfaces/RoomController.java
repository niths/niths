package no.niths.application.rest.location.interfaces;

import no.niths.application.rest.interfaces.GenericRESTController;
import no.niths.domain.location.Room;
import no.niths.domain.signaling.AccessField;

/**
 * Controller for room
 * has the basic CRUD methods and
 * methods too add and remove accessField
 * in addition too method for findRoom
 *
 * For the URL too get Room add /rooms
 * after the {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}
 */
public interface RoomController extends GenericRESTController<Room> {

    /**
     * Adds a accessField too an room
     *
     * Too add accessField add /{roomId}/accessfield/{afId}
     * too the URL
     *
     * Use the POST method
     *
     * @param roomId id of the exam
     * @param afId if of the room
     */
    void addAccessField(long roomId, long afId);

    /**
     * Removes a accessField from an room
     *
     * Too remove accessField add /{roomId}/accessfield/{afId}
     * too the URL
     *
     * Use the DELETE method
     *
     * @param roomId id of the exam
     * @param afId if of the room
     */
    void removeAccessField(long roomId, long afId);

    /**
     * Returns a room with specific accessField
     *
     * Too get a room with specific accessField add /search/accesspoint
     * too the URL
     *
     * Use the GET method
     *
     * @param accessField that should return a room
     * @return a room that has a specific accessField
     */
    Room findRoom(AccessField accessField);
}