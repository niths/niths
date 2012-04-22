package no.niths.application.rest.location;

import no.niths.application.rest.AbstractRESTControllerImpl;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.lists.location.RoomList;
import no.niths.application.rest.location.interfaces.RoomController;
import no.niths.common.constants.AppNames;
import no.niths.domain.location.Room;
import no.niths.domain.signaling.AccessField;
import no.niths.services.interfaces.GenericService;
import no.niths.services.location.interfaces.RoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(AppNames.ROOMS)
public class RoomControllerImpl extends AbstractRESTControllerImpl<Room>
        implements RoomController {

    @Autowired
    private RoomService service;

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
    @RequestMapping(value = "{roomId}/add/accessfield/{accessFieldId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "AcessField added")
    public void addAccessField(@PathVariable long roomId,
            @PathVariable long accessFieldId) {    
        service.addAccessField(roomId, accessFieldId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "{roomId}/remove/accessfield/{accessFieldId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Access Field Removed")
    public void removeAccessField(@PathVariable long roomId,
            @PathVariable long accessFieldId) {
        service.removeAccessField(roomId, accessFieldId);
    }

    @Override
    @RequestMapping(
            value  = "search/accesspoint")
    @ResponseBody
    public Room findRoom(AccessField accessField) {
        return service.getRoom(accessField);
    }
}