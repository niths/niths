package no.niths.services.battlestation;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.GregorianCalendar;

import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.battlestation.Console;
import no.niths.domain.battlestation.Game;
import no.niths.domain.battlestation.Loan;
import no.niths.services.battlestation.interfaces.ConsoleService;
import no.niths.services.battlestation.interfaces.GameService;
import no.niths.services.battlestation.interfaces.LoanService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class GameServiceTest {

    public static final String NAME = "Super Mario";
    public static final String CHANGED_NAME = "Halo";

    @Autowired
    private GameService gameService;

    @Autowired
    private ConsoleService consoleService;

    @Autowired
    private LoanService loanService;

    @Test
    public void testCRUD(){

        int size = gameService.getAll(null).size();

        Game game = new Game();
        game.setTitle(NAME);
        gameService.create(game);
        assertThat(size + 1, is(equalTo(gameService.getAll(null).size())));

        Game tempGame = gameService.getById(game.getId());
        assertThat(NAME, is(equalTo(tempGame.getTitle())));

        tempGame.setTitle(CHANGED_NAME);
        gameService.update(tempGame);

        tempGame = gameService.getById(game.getId());
        assertThat(CHANGED_NAME, is(equalTo(tempGame.getTitle())));

        gameService.hibernateDelete(game.getId());
        assertThat(size, is(equalTo(gameService.getAll(null).size())));
    }

    @Test
    public void testRelationsBetweenGameAndConsole(){
        Console console = new Console("Wii");
        consoleService.create(console);

        Game game = new Game();
        game.setTitle(NAME);
        gameService.create(game);

        game.setConsole(console);
        gameService.update(game);

        assertThat(consoleService.getById(console.getId()), is(equalTo(gameService.getById(game.getId()).getConsole())));

        gameService.hibernateDelete(game.getId());
        consoleService.hibernateDelete(console.getId());
    }

    @Test
    public void testRelationsBetweenGameAndLoan(){
        Loan loan = new Loan(new GregorianCalendar());
        loanService.create(loan);

        Game game = new Game(NAME);
        gameService.create(game);

        //game.setLoan(loan);
        gameService.update(game);

        //assertThat(loanService.getById(loan.getId()), is(equalTo(gameService.getById(game.getId()).getLoan())));

        gameService.hibernateDelete(game.getId());
        loanService.hibernateDelete(loan.getId());
    }
}
