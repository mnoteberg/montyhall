package se.ith.montyhall;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.primitives.Ints;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        if (args.length != 1) {
            LOGGER.error("Illegal usage. Should be one argument.");
            return;
        }

        Integer numberOfGames = Ints.tryParse(args[0]);
        if (numberOfGames == null) {
            LOGGER.error("Wrong usage. Argument should be an integer.");
            return;
        }

        double percentageKeepingFirstChoice;
        double percentageWhenChangingBox;

        try {
            GameShowSimulation gameSimulation = new GameShowSimulation(numberOfGames);
            percentageKeepingFirstChoice = gameSimulation.runSimulation(GameStrategy.KEEP_FIRST_CHOICE);
            percentageWhenChangingBox = gameSimulation.runSimulation(GameStrategy.CHANGE_OF_HEARTS);
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage());
            return;
        }

        if (percentageKeepingFirstChoice == percentageWhenChangingBox) {
            LOGGER.info("Equal chance to win.");
        } else if (percentageKeepingFirstChoice > percentageWhenChangingBox) {
            LOGGER.info("Better chance to win when keeping first choice: {}%", percentageKeepingFirstChoice);
        } else {
            LOGGER.info("Better chance to win when changing box: {}%", percentageWhenChangingBox);
        }
    }

}
