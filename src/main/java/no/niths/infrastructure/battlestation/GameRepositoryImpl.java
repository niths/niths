package no.niths.infrastructure.battlestation;

import no.niths.domain.battlestation.Game;
import no.niths.infrastructure.AbstractGenericRepositoryImpl;
import no.niths.infrastructure.battlestation.interfaces.GameRepository;

import org.springframework.stereotype.Repository;
/**
 * Repository class for Game
 *
 * <p>
 * Inherits the basic CRUD actions
 * </p>
 */
@Repository
public class GameRepositoryImpl extends AbstractGenericRepositoryImpl<Game>  implements GameRepository {

    public GameRepositoryImpl() {
        super(Game.class, new Game());
    }
}
