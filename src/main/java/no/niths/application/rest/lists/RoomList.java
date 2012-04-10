package no.niths.application.rest.lists;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.AppConstants;
import no.niths.domain.location.Room;

@XmlRootElement(name = AppConstants.ROOMS)
public class RoomList extends ListAdapter<Room> {

	private static final long serialVersionUID = 6226301072582936953L;
	@SuppressWarnings("unused")
	@XmlElement(name = "room")
	private List<Room> roomData;

	@Override
	public void setData(List<Room> roomData) {
		this.roomData = roomData;
	}

    @Override
    public List<Room> getData() {
        // TODO Auto-generated method stub
        return null;
    }
}