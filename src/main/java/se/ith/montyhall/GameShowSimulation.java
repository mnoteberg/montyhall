package se.ith.montyhall;

import java.util.Random;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameShowSimulation {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameShowSimulation.class);

    private static final int MIN_NUM_GAMES = 10;
    private static final int MAX_NUM_GAMES = 1000000;

    private final int numberOfGames;
    private Random ran = new Random();

    /**
     * Initialize a game show simulation with {@code numberOfGames} games.
     *
     * @param numberOfGames The number of games to run in a simulation.
     */
    public GameShowSimulation(int numberOfGames) {
        if (numberOfGames < MIN_NUM_GAMES || numberOfGames > MAX_NUM_GAMES) {
            throw new IllegalArgumentException(
                    String.format("Invalid number of games. Should be %d < i ≤ %d", MIN_NUM_GAMES, MAX_NUM_GAMES));
        }
        this.numberOfGames = numberOfGames;
    }

    /**
     * Run a simulation with {@link #numberOfGames} games and the specified {@link GameStrategy}.
     *
     * @param gameStrategy The game strategy to use for the simulation.
     * @return the percentage of games won using the specified {@link GameStrategy}.
     */
    public double runSimulation(GameStrategy gameStrategy) {
        if (gameStrategy == null) {
            throw new IllegalArgumentException("A game strategy must be provided.");
        }

        long numberOfGamesWon = IntStream.generate(() -> ran.nextInt(3)).limit(numberOfGames).mapToObj(i -> playGame(i, gameStrategy))
                .filter(Boolean::valueOf).count();
        double percentage = ((double) 100 * numberOfGamesWon) / numberOfGames;

        LOGGER.info("{} games won out of {} ({}%) when applying strategy {}", numberOfGamesWon, numberOfGames, percentage, gameStrategy);
        return percentage;
    }

    /**
     * Utility method for simulating one game with a random starting box, and the supplied {@link GameStrategy}.
     *
     * @param firstSelectedBox Index of the players first selected box.
     * @param gameStrategy The game strategy the player will use.
     * @return {@code true} if the player won this game, {@code false} otherwise.
     */
    private boolean playGame(int firstSelectedBox, GameStrategy gameStrategy) {
        GameShow gameShow = new GameShow(ran);
        gameShow.selectBox(firstSelectedBox);
        gameShow.letHostOpenBox();
        if (gameStrategy == GameStrategy.CHANGE_OF_HEARTS) {
            gameShow.changeSelectedBox();
        }
        return gameShow.wonGame();
    }
}
