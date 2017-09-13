package se.ith.montyhall;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Random;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class GameShowSimulationTest {

    @Test(expected = IllegalArgumentException.class)
    public void testGameShowSimulationTooFewGames() {
        new GameShowSimulation(9);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGameShowSimulationTooManyGames() {
        new GameShowSimulation(1000001);
    }

    @Test
    public void testRunSimulationKeepFirstBox() {
        GameShowSimulation gameShowSimulation = new GameShowSimulation(10000);
        assertNotNull("Should return percentage of games won", gameShowSimulation.runSimulation(GameStrategy.KEEP_FIRST_CHOICE));
    }

    @Test
    public void testRunSimulationChangeOfHearts() {
        GameShowSimulation gameShowSimulation = new GameShowSimulation(10000);
        assertNotNull("Should return percentage of games won", gameShowSimulation.runSimulation(GameStrategy.CHANGE_OF_HEARTS));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRunSimulationGameStrategyMissing() {
        new GameShowSimulation(100).runSimulation(null);
    }

    @Test
    public void testRunSimulationKeepFirstBoxFixedSeed() {
        GameShowSimulation gameShowSimulation = new GameShowSimulation(10000);
        ReflectionTestUtils.setField(gameShowSimulation, "ran", new Random(100L));
        assertEquals("Should return percentage of games won", 34.01, gameShowSimulation.runSimulation(GameStrategy.KEEP_FIRST_CHOICE), 0.1);
    }

    @Test
    public void testRunSimulationChangeOfHeartsFixedSeed() {
        GameShowSimulation gameShowSimulation = new GameShowSimulation(10000);
        ReflectionTestUtils.setField(gameShowSimulation, "ran", new Random(100L));
        assertEquals("Should return percentage of games won", 65.99, gameShowSimulation.runSimulation(GameStrategy.CHANGE_OF_HEARTS), 0.1);
    }
}
