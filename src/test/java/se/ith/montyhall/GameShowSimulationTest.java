package se.ith.montyhall;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

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
        assertNotNull("Should return number of games won", gameShowSimulation.runSimulation(GameStrategy.KEEP_FIRST_CHOICE));
    }

    @Test
    public void testRunSimulationChangeOfHearts() {
        GameShowSimulation gameShowSimulation = new GameShowSimulation(10000);
        assertNotNull("Should return number of games won", gameShowSimulation.runSimulation(GameStrategy.CHANGE_OF_HEARTS));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRunSimulationGameStrategyMissing() {
        new GameShowSimulation(100).runSimulation(null);
    }

}
