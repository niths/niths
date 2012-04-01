package no.niths.services;

import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.Console;
import no.niths.domain.Game;
import no.niths.services.interfaces.ConsoleService;
import no.niths.services.interfaces.GameService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class GameServiceTest {

    private static final Logger logger = LoggerFactory
            .getLogger(GameServiceTest.class);

    public static final String NAME = "Super Mario";
    public static final String CHANGED_NAME = "Halo";

    @Autowired
    private GameService gameService;

    @Autowired
    private ConsoleService consoleService;

    @Test
    public void testCRUD(){

        int size = gameService.getAll(null).size();

        Game game = new Game();
        game.setName(NAME);
        gameService.create(game);
        assertThat(size + 1, is(equalTo(gameService.getAll(null).size())));

        Game tempGame = gameService.getById(game.getId());
        assertThat(NAME, is(equalTo(tempGame.getName())));

        tempGame.setName(CHANGED_NAME);
        gameService.update(tempGame);

        tempGame = gameService.getById(game.getId());
        assertThat(CHANGED_NAME, is(equalTo(tempGame.getName())));

        gameService.hibernateDelete(game.getId());
        assertThat(size, is(equalTo(gameService.getAll(null).size())));
    }

    @Test
    public void testRelationsBetweenGameAndConsole(){
        Console console = new Console("Wii");
        consoleService.create(console);

        Game game = new Game();
        game.setName(NAME);
        gameService.create(game);

        game.setConsole(console);
        gameService.update(game);

        assertThat(consoleService.getById(console.getId()), is(equalTo(gameService.getById(game.getId()).getConsole())));

        gameService.hibernateDelete(game.getId());
        consoleService.hibernateDelete(console.getId());
    }
}
