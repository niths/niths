package no.niths.application.rest.lists.location;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.constants.DomainConstantNames;
import no.niths.domain.location.Room;
/**
 * Class to contain a list of rooms
 */
@XmlRootElement(name = DomainConstantNames.ROOMS)
public class RoomList extends ListAdapter<Room> {

    private static final long serialVersionUID = 6226301072582936953L;

    @SuppressWarnings("unused")
    @XmlElement(name = "room")
    private List<Room> roomData;

    @Override
    public void setData(List<Room> roomData) {
        this.roomData = roomData;
    }
}