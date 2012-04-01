package no.niths.infrastructure;

import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.Game;
import no.niths.infrastructure.interfaces.GameRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager")
public class GameRepositoryTest {

    private static final Logger logger = LoggerFactory
            .getLogger(GameRepositoryTest.class);

    public static final String NAME = "Super Mario";
    public static final String CATEGORY = "Action";

    @Autowired
    private GameRepository gameRepository;

    @Test(expected = IllegalArgumentException.class)
    public void whenInsertNull_persistenceShouldFail() {
        gameRepository.create(null);
    }

    @Test
    public void whenGameIsCreated_GameShouldBePersisted() {
        int size = gameRepository.getAll(null).size();

        Game game = new Game();
        game.setName("Super Mario");
        gameRepository.create(game);

        assertThat(size + 1, is(equalTo(gameRepository.getAll(null).size())));
    }

    @Test
    public void whenGetById_GameShouldBeReturned() {
        int size = gameRepository.getAll(null).size();

        Game game = new Game();
        game.setName(NAME);
        game.setCategory(CATEGORY);
        gameRepository.create(game);

        assertThat(size + 1, is(equalTo(gameRepository.getAll(null).size())));

        Game result = gameRepository.getById(game.getId());
        assertThat(result, is(equalTo(game)));

        result = gameRepository.getById(999L);
        assertThat(result, is(equalTo(null)));
    }

    @Test
    public void whenGameIsUpdated_GameShouldBeUpdated() {
        int size = gameRepository.getAll(null).size();

        Game game = new Game();
        game.setName(NAME);
        game.setCategory(CATEGORY);
        gameRepository.create(game);

        assertThat(size + 1, is(equalTo(gameRepository.getAll(null).size())));

        game.setCategory("Drama");
        gameRepository.update(game);

        assertThat("Drama", is(equalTo(gameRepository.getById(game.getId()).getCategory())));
    }

    @Test
    public void whenGetAll_allShouldBeReturned() {
        int size = gameRepository.getAll(null).size();

        Game game = new Game();
        game.setName(NAME);
        gameRepository.create(game);
        Game otherGame = new Game();
        otherGame.setName("Halo");
        gameRepository.create(otherGame);
        Game thirdGame = new Game();
        thirdGame.setName("Java 3");
        gameRepository.create(thirdGame);

        assertThat(size + 3, is(equalTo(gameRepository.getAll(null).size())));

        assertThat(size + 1, is(equalTo(gameRepository.getAll(game).size())));
    }
}