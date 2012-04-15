package no.niths.application.rest.location;

import no.niths.application.rest.AbstractRESTControllerImpl;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.helper.Error;
import no.niths.application.rest.helper.Status;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.lists.RoomList;
import no.niths.application.rest.location.interfaces.RoomController;
import no.niths.common.AppConstants;
import no.niths.common.ValidationHelper;
import no.niths.domain.location.Room;
import no.niths.domain.signaling.AccessField;
import no.niths.services.interfaces.GenericService;
import no.niths.services.location.interfaces.RoomService;
import no.niths.services.signaling.interfaces.AccessFieldService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(AppConstants.ROOMS)
public class RoomControllerImpl extends AbstractRESTControllerImpl<Room>
        implements RoomController {

    private static final Logger logger = LoggerFactory
            .getLogger(RoomControllerImpl.class);

    @Autowired
    private RoomService service;

    @Autowired
    private AccessFieldService accessFieldService;

    private RoomList roomList = new RoomList();

    @Override
    public GenericService<Room> getService() {
        return service;
    }

    @Override
    public ListAdapter<Room> getList() {
        return roomList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(
            value  = "{roomId}/add-accessfield/{accessFieldId}",
            method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "AcessField added")
    public void addAccessField(
            @PathVariable long roomId,
            @PathVariable long accessFieldId) {

        Room room = service.getById(roomId);
        ValidationHelper.isObjectNull(
                buildErrorMsg(Room.class, Error.DOES_NOT_EXIST));

        AccessField accessField = accessFieldService.getById(accessFieldId);
        ValidationHelper.isObjectNull(
                buildErrorMsg(AccessField.class, Error.DOES_NOT_EXIST));

        room.getAccessFields().add(accessField);
        service.update(room);
        logger.debug(buildStatusMsg(Room.class, Status.UPDATED));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(
            value  = "remove-accessfield/{roomId}/{accessFieldId}",
            method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Access Field Removed")
    public void removeAccessField(
            @PathVariable long roomId,
            @PathVariable long accessFieldId) {
        service.removeAccessField(roomId, accessFieldId);
    }
}