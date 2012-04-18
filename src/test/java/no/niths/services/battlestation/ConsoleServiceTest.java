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
public class ConsoleServiceTest {

    public static final String NAME = "Wii";
    public static final String CHANGED_NAME = "Xbox";

    @Autowired
    private ConsoleService consoleService;

    @Autowired
    private GameService gameService;

    @Autowired
    private LoanService loanService;

    @Test
    public void testCRUD(){

        int size = consoleService.getAll(null).size();

        Console console = new Console();
        console.setName(NAME);
        consoleService.create(console);
        assertThat(size + 1, is(equalTo(consoleService.getAll(null).size())));

        Console tempConsole = consoleService.getById(console.getId());
        assertThat(NAME, is(equalTo(tempConsole.getName())));

        tempConsole.setName(CHANGED_NAME);
        consoleService.update(tempConsole);

        tempConsole = consoleService.getById(console.getId());
        assertThat(CHANGED_NAME, is(equalTo(tempConsole.getName())));

        consoleService.hibernateDelete(console.getId());
        assertThat(size, is(equalTo(consoleService.getAll(null).size())));
    }

    @Test
    public void testRelationsBetweenConsoleAndGame(){
        Game game = new Game("Super Mario");
        gameService.create(game);

        Game otherGame = new Game("Halo");
        gameService.create(otherGame);

        Console console = new Console(NAME);
        consoleService.create(console);

        console.getGames().add(game);
        console.getGames().add(otherGame);
        consoleService.update(console);

        assertThat(2, is(equalTo(consoleService.getById(console.getId()).getGames().size())));

        consoleService.hibernateDelete(console.getId());
        gameService.hibernateDelete(game.getId());
        gameService.hibernateDelete(otherGame.getId());
    }

    @Test
    public void testRelationsBetweenConsoleAndLoan(){
        Loan loan = new Loan(new GregorianCalendar());
        loanService.create(loan);

        Console console = new Console();
        console.setName(NAME);
        consoleService.create(console);

        console.setLoan(loan);
        consoleService.update(console);

        assertThat(loanService.getById(loan.getId()), is(equalTo(consoleService.getById(console.getId()).getLoan())));

        consoleService.hibernateDelete(console.getId());
        loanService.hibernateDelete(loan.getId());
    }
}
