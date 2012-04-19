package no.niths.application.rest.location.interfaces;

import no.niths.application.rest.interfaces.GenericRESTController;
import no.niths.domain.location.Room;
import no.niths.domain.signaling.AccessField;

/**
 * 
 * Controller for Room
 *
 */
public interface RoomController extends GenericRESTController<Room> {

    void addAccessField(long roomId, long afId);

    void removeAccessField(long roomId, long afId);

    Room findRoom(AccessField accessField);
}