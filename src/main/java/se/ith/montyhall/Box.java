package se.ith.montyhall;

public class Box {

    private boolean open;
    private final boolean hasMoney;

    /**
     * Initialize a box with or without money in it. A newly initialized box will never be open.
     *
     * @param hasMoney whether the box should contain money ({@code true}) or not ({@code false}).
     */
    public Box(boolean hasMoney) {
        this.hasMoney = hasMoney;
        this.open = false;
    }

    /**
     * Check if this box has been opened.
     *
     * @return {@code true} if this box has been opened, {@code false} otherwise.
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * Check if this box has money in it.
     *
     * @return {@code true} if this box has money in it, {@code false} otherwise.
     */
    public boolean isHasMoney() {
        return hasMoney;
    }

    /**
     * Let the game show host open a box for the player to see that it's empty.
     */
    public void openBox() {
        if (isHasMoney()) {
            throw new IllegalStateException("Not allowed to open the box with money in it.");
        }
        open = true;
    }
}
