package se.ith.montyhall;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BoxTest {

    @Test(expected = IllegalStateException.class)
    public void testBoxWithMoneyNotAllowedToBeOpened() {
        Box box = new Box(true);
        assertTrue("Newly initialized box should have money", box.isHasMoney());
        assertFalse("Newly initialized box should not be open", box.isOpen());

        box.openBox();
    }

    @Test
    public void testBoxIsClosedUntilOpenedWithoutMoney() {
        Box box = new Box(false);
        assertFalse("Newly initialized box should not have money", box.isHasMoney());
        assertFalse("Newly initlaized box should not be open", box.isOpen());

        box.openBox();

        assertFalse("Box should still not have any money in it", box.isHasMoney());
        assertTrue("Box should be opened", box.isOpen());
    }
}
