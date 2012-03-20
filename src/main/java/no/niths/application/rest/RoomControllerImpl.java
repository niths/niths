package no.niths.application.rest;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import no.niths.application.rest.interfaces.RoomController;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.lists.RoomList;
import no.niths.common.AppConstants;
import no.niths.domain.location.Room;
import no.niths.services.interfaces.GenericService;
import no.niths.services.interfaces.RoomService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(AppConstants.ROOMS)
public class RoomControllerImpl extends AbstractRESTControllerImpl<Room> implements RoomController{

    private static final Logger logger = LoggerFactory
            .getLogger(RoomControllerImpl.class);

    @Autowired
    private RoomService service;

    private RoomList roomList = new RoomList();

    @Override
    public ArrayList<Room> getAll(Room domain) {
    	logger.debug("size first : " + roomList.size());
    	
    	ArrayList<Room> tempList = super.getAll(domain);
    	
    	logger.debug("size after : " + roomList.size());
//    	LinkedHashSet<Room> hashSet = new LinkedHashSet<Room>(super.getAll(domain));
//    
//    	roomList.clear();
//    	roomList.addAll(hashSet);
//    	logger.debug("size after hasSet : " + roomList.size());
    	return tempList;
    }
    
    @Override
    public GenericService<Room> getService() {
        return service;
    }

    @Override
    public ListAdapter<Room> getList() {
        return roomList;
    }
}