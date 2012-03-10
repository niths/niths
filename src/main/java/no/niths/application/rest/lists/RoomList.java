package no.niths.application.rest.lists;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.AppConstants;
import no.niths.domain.Room;

@XmlRootElement(name = AppConstants.ROOMS)
public class RoomList extends ListAdapter<Room> {

    @XmlElement(name = "room")
    private List<Room> roomData;

    @Override
    public void setData(List<Room> roomData) {
        this.roomData = roomData;
    }
}