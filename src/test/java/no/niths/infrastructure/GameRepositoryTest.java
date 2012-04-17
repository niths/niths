package no.niths.infrastructure;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.battlestation.Game;
import no.niths.domain.battlestation.GameCategory;
import no.niths.infrastructure.battlestation.interfaces.GameRepository;

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
public class GameRepositoryTest {

    public static final String NAME = "Super Mario";
    public static final GameCategory CATEGORY = GameCategory.ACTION;

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
        game.setTitle("Super Mario");
        gameRepository.create(game);

        assertThat(size + 1, is(equalTo(gameRepository.getAll(null).size())));
    }

    @Test
    public void whenGetById_GameShouldBeReturned() {
        int size = gameRepository.getAll(null).size();

        Game game = new Game();
        game.setTitle(NAME);
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
        game.setTitle(NAME);
        game.setCategory(CATEGORY);
        gameRepository.create(game);

        assertThat(size + 1, is(equalTo(gameRepository.getAll(null).size())));

        game.setCategory(GameCategory.ACTION);
        gameRepository.update(game);

        assertThat(GameCategory.ACTION, is(equalTo(gameRepository.getById(game.getId()).getCategory())));
    }

    @Test
    public void whenGetAll_allShouldBeReturned() {
        int size = gameRepository.getAll(null).size();

        Game game = new Game();
        game.setTitle(NAME);
        gameRepository.create(game);
        Game otherGame = new Game();
        otherGame.setTitle("Halo");
        gameRepository.create(otherGame);
        Game thirdGame = new Game();
        thirdGame.setTitle("Java 3");
        gameRepository.create(thirdGame);

        assertThat(size + 3, is(equalTo(gameRepository.getAll(null).size())));

        assertThat(size + 1, is(equalTo(gameRepository.getAll(game).size())));
    }
}
