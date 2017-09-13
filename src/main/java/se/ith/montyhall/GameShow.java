package se.ith.montyhall;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.google.common.collect.Lists;

public class GameShow {

    private List<Box> boxes;
    private Optional<Box> selected = Optional.empty();

    /**
     * Initializes a game show, with three boxes out of which a random one has money in it.
     */
    public GameShow(Random ran) {
        if (ran == null) {
            throw new IllegalArgumentException("Must provide a random seed.");
        }
        initAndShuffleBoxes(ran);
    }

    /**
     * Let player select a first box.
     *
     * @param index Index of the box selected. Should be {@code 0 < index < 3} since there are three boxes.
     */
    public void selectBox(int index) {
        if (index < 0 || index >= boxes.size()) {
            throw new IllegalArgumentException("Invalid box to select.");
        }
        selected = Optional.of(boxes.get(index));
    }

    /**
     * Check if player won this game.
     *
     * @return {@code true} if the player won, {@code false} otherwise.
     */
    public boolean wonGame() {
        return selected.map(Box::isHasMoney)
                .orElseThrow(() -> new IllegalStateException("Can't check if won before actually selecting a box."));
    }

    /**
     * Let the game show host open a box after the player selected a first box.
     */
    public void letHostOpenBox() {
        if (!isAnyBoxSelected() || thereIsAtleastOneOpenBox()) {
            throw new IllegalStateException("Game show host won't open a box until the player selects a box, or if a box has already been opened.");
        }
        boxes.stream().filter(b -> b != selected.get() && !b.isHasMoney()).findAny().ifPresent(Box::openBox);
    }

    /**
     * Let the player change his/her selection. Will select the box that is not already selected neither opened.
     */
    public void changeSelectedBox() {
        if (!isAnyBoxSelected() || !thereIsAtleastOneOpenBox()) {
            throw new IllegalStateException("Player can't change selection if no box is selected, or if the game show host hasn't opened any boxes yet.");
        }
        selected = boxes.stream().filter(b -> b != selected.get() && !b.isOpen()).findAny();
    }

    /**
     * Check whether the player has selected a box.
     *
     * @return {@code true} if the player has selected a box, {@code false} otherwise.
     */
    public boolean isAnyBoxSelected() {
        return selected.isPresent();
    }

    /**
     * Check whether there is at least one opened box.
     *
     * @return {@code true} if at least one box is opened, {@code false} otherwise.
     */
    public boolean thereIsAtleastOneOpenBox() {
        return boxes.stream().anyMatch(Box::isOpen);
    }

    /**
     * Create and shuffle the initial set of boxes. One box with money, two without.
     */
    private void initAndShuffleBoxes(Random ran) {
        boxes = Lists.newArrayList(new Box(true), new Box(false), new Box(false));
        Collections.shuffle(boxes, ran);
    }

}
