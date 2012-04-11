package no.niths.services;

import no.niths.domain.Game;
import no.niths.infrastructure.interfaces.GameRepository;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.services.interfaces.GameService;
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

           /* if (game.getLoanedBy() != null) {
                game.getLoanedBy().getLastName();
            }*/
        }
        return game;
    }

    @Override
    public GenericRepository<Game> getRepository() {
        return gameRepository;
    }
}
