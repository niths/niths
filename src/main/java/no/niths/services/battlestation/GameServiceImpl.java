package no.niths.services.battlestation;

import no.niths.domain.battlestation.Game;
import no.niths.infrastructure.interfaces.GameRepository;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.services.AbstractGenericService;
import no.niths.services.battlestation.interfaces.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GameServiceImpl extends AbstractGenericService<Game> implements GameService {

    @Autowired
    private GameRepository gameRepository;

    public Game getById(long id) {
        Game game = gameRepository.getById(id);

        if (game != null) {
            if (game.getConsole() != null) {
                game.getConsole().getName();
            }
        }
        return game;
    }

    @Override
    public GenericRepository<Game> getRepository() {
        return gameRepository;
    }
}
