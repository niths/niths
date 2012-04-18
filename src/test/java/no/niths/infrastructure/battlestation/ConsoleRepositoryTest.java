package no.niths.infrastructure.battlestation;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.battlestation.Console;
import no.niths.infrastructure.battlestation.interfaces.ConsoleRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager")
public class ConsoleRepositoryTest {

    public static final String NAME = "Wii";

    @Autowired
    private ConsoleRepository consoleRepository;

    @Test(expected = IllegalArgumentException.class)
    public void whenInsertNull_persistenceShouldFail() {
        consoleRepository.create(null);
    }

    @Test
    public void whenConsoleIsCreated_ConsoleShouldBePersisted() {
        int size = consoleRepository.getAll(null).size();

        Console console = new Console();
        console.setName(NAME);
        consoleRepository.create(console);

        assertThat(size + 1, is(equalTo(consoleRepository.getAll(null).size())));
    }

    @Test
    public void whenGetById_ConsoleShouldBeReturned() {
        int size = consoleRepository.getAll(null).size();

        Console console = new Console();
        console.setName(NAME);
        consoleRepository.create(console);

        assertThat(size + 1, is(equalTo(consoleRepository.getAll(null).size())));

        Console result = consoleRepository.getById(console.getId());
        assertThat(result, is(equalTo(console)));

        result = consoleRepository.getById(999L);
        assertThat(result, is(equalTo(null)));
    }

    @Test
    public void whenConsoleIsUpdated_ConsoleShouldBeUpdated() {
        int size = consoleRepository.getAll(null).size();

        Console console = new Console();
        console.setName(NAME);
        consoleRepository.create(console);

        assertThat(size + 1, is(equalTo(consoleRepository.getAll(null).size())));

        console.setName("PS2");
        consoleRepository.update(console);

        assertThat("PS2", is(equalTo(consoleRepository.getById(console.getId()).getName())));
    }

    @Test
    public void whenGetAll_allShouldBeReturned() {
        int size = consoleRepository.getAll(null).size();

        Console console = new Console();
        console.setName(NAME);
        consoleRepository.create(console);
        Console otherConsole = new Console();
        otherConsole.setName("PS2");
        consoleRepository.create(otherConsole);
        Console thirdConsole = new Console();
        thirdConsole.setName("Xbox");
        consoleRepository.create(thirdConsole);

        assertThat(size + 3, is(equalTo(consoleRepository.getAll(null).size())));

        assertThat(size + 1, is(equalTo(consoleRepository.getAll(console).size())));
    }
}
