package no.niths.infrastructure;

import no.niths.domain.Game;
import no.niths.infrastructure.interfaces.GameRepository;
import org.springframework.stereotype.Repository;

@Repository
public class GameRepositoryImpl extends AbstractGenericRepositoryImpl<Game>  implements GameRepository {

    public GameRepositoryImpl() {
        super(Game.class, new Game());
    }
}
