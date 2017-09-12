package se.ith.montyhall;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GameShowTest {

    @Test(expected = IllegalArgumentException.class)
    public void testSelectBoxNegativeIndex() {
        new GameShow().selectBox(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectBoxIndexTooHigh() {
        new GameShow().selectBox(3);
    }

    @Test
    public void testSelectBox() {
        GameShow gameShow = new GameShow();

        assertFalse("Newly initialized game show should not have a player selected box yet", gameShow.isAnyBoxSelected());

        gameShow.selectBox(0);

        assertTrue("A box should have been selected", gameShow.isAnyBoxSelected());
    }

    @Test(expected = IllegalStateException.class)
    public void testLetHostOpenBoxNoBoxSelected() {
        new GameShow().letHostOpenBox();
    }

    @Test
    public void testLetHostOpenBox() {
        GameShow gameShow = new GameShow();

        assertFalse("Newly initialized game show should not have any open boxes", gameShow.thereIsAtleastOneOpenBox());

        gameShow.selectBox(1);
        gameShow.letHostOpenBox();

        assertTrue("A box should have been opened", gameShow.thereIsAtleastOneOpenBox());
    }

    @Test(expected = IllegalStateException.class)
    public void testLetHostOpenBoxAlreadyOpen() {
        GameShow gameShow = new GameShow();

        gameShow.selectBox(2);
        // not allowed to open two boxes
        gameShow.letHostOpenBox();
        gameShow.letHostOpenBox();
    }

    @Test(expected = IllegalStateException.class)
    public void testWonGameNoBoxSelected() {
        new GameShow().wonGame();
    }

    @Test
    public void testWonGame() {
        GameShow gameShow = new GameShow();

        gameShow.selectBox(0);

        assertNotNull(gameShow.wonGame());
    }

    @Test(expected = IllegalStateException.class)
    public void testChangeSelectedBoxNoBoxSelected() {
        new GameShow().changeSelectedBox();
    }

    @Test(expected = IllegalStateException.class)
    public void testChangeSelectedBoxBeforeHostOpensBox() {
        GameShow gameShow = new GameShow();

        gameShow.selectBox(1);
        gameShow.changeSelectedBox();
    }

    @Test
    public void testChangeSelectedBox() {
        GameShow gameShow = new GameShow();

        gameShow.selectBox(2);
        gameShow.letHostOpenBox();
        gameShow.changeSelectedBox();

        assertTrue("A box should be selected", gameShow.isAnyBoxSelected());
        assertTrue("There should be a opened box", gameShow.thereIsAtleastOneOpenBox());
    }
}
