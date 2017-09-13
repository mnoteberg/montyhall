package se.ith.montyhall;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

public class GameShowTest {

    private Random ran = new Random();

    @Test(expected = IllegalArgumentException.class)
    public void testGameShowRandNull() {
        new GameShow(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectBoxNegativeIndex() {
        new GameShow(ran).selectBox(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectBoxIndexTooHigh() {
        new GameShow(ran).selectBox(3);
    }

    @Test
    public void testSelectBox() {
        GameShow gameShow = new GameShow(ran);

        assertFalse("Newly initialized game show should not have a player selected box yet", gameShow.isAnyBoxSelected());

        gameShow.selectBox(0);

        assertTrue("A box should have been selected", gameShow.isAnyBoxSelected());
    }

    @Test(expected = IllegalStateException.class)
    public void testLetHostOpenBoxNoBoxSelected() {
        new GameShow(ran).letHostOpenBox();
    }

    @Test
    public void testLetHostOpenBox() {
        GameShow gameShow = new GameShow(ran);

        assertFalse("Newly initialized game show should not have any open boxes", gameShow.thereIsAtleastOneOpenBox());

        gameShow.selectBox(1);
        gameShow.letHostOpenBox();

        assertTrue("A box should have been opened", gameShow.thereIsAtleastOneOpenBox());
    }

    @Test(expected = IllegalStateException.class)
    public void testLetHostOpenBoxAlreadyOpen() {
        GameShow gameShow = new GameShow(ran);

        gameShow.selectBox(2);
        // not allowed to open two boxes
        gameShow.letHostOpenBox();
        gameShow.letHostOpenBox();
    }

    @Test(expected = IllegalStateException.class)
    public void testWonGameNoBoxSelected() {
        new GameShow(ran).wonGame();
    }

    @Test
    public void testWonGame() {
        GameShow gameShow = new GameShow(ran);

        gameShow.selectBox(0);

        assertNotNull(gameShow.wonGame());
    }

    @Test(expected = IllegalStateException.class)
    public void testChangeSelectedBoxNoBoxSelected() {
        new GameShow(ran).changeSelectedBox();
    }

    @Test(expected = IllegalStateException.class)
    public void testChangeSelectedBoxBeforeHostOpensBox() {
        GameShow gameShow = new GameShow(ran);

        gameShow.selectBox(1);
        gameShow.changeSelectedBox();
    }

    @Test
    public void testChangeSelectedBox() {
        GameShow gameShow = new GameShow(ran);

        gameShow.selectBox(2);
        gameShow.letHostOpenBox();
        gameShow.changeSelectedBox();

        assertTrue("A box should be selected", gameShow.isAnyBoxSelected());
        assertTrue("There should be a opened box", gameShow.thereIsAtleastOneOpenBox());
    }
}
