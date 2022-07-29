package business;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrapTest {

    Trap t;
    Player p;

    @BeforeEach
    void setUp() {
        t = new Trap('B', "Bonus business.Trap", 1, 1, 1, 250,  1, 10);
        p = new Warrior("Jon Snow", 300, 30, 4, 3);
    }

    @Test
    void move() {
        t.ticksCount = 1;
        t.visibilityTime = 2;
        t.move(p);
        Assertions.assertEquals(true, t.visible, "Trap isn't visible despite ticksCount < visibilityTime.");
        Assertions.assertEquals(2, t.ticksCount, "Trap isn't visible despite ticksCount < visibilityTime.");

        t.invisibilityTime = 2;
        t.ticksCount = 4;
        t.move(p);
        Assertions.assertEquals(false, t.visible, "Trap is visible despite ticksCount > visibilityTime.");
        Assertions.assertEquals(0, t.ticksCount, "Trap didn't reset ticksCount despite ticksCount = visibilityTime + invisibilityTime.");
    }

    @Test
    void testToString() {
        t.visible = true;
        Assertions.assertEquals("B", t.toString(), "Trap didn't print properly of visible.");

        t.visible = false;
        Assertions.assertEquals(".", t.toString(), "Trap didn't print properly of invisible.");
    }
}