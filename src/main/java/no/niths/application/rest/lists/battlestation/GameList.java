package no.niths.application.rest.lists.battlestation;

import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.constants.DomainConstantNames;
import no.niths.domain.battlestation.Game;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = DomainConstantNames.GAMES)
public class GameList extends ListAdapter<Game> {

    private static final long serialVersionUID = 6250108293263726953L;

    @SuppressWarnings("unused")
    @XmlElement(name = "game")
    private List<Game> data;

    @Override
    public void setData(List<Game> games) {
        this.data = games;
    }
}