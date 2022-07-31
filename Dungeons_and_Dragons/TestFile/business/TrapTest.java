package business;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrapTest {

    TileFactory tf = new TileFactory();
    MessageCallback ms = new MessageCallback() {
        public void print(String message) {
            System.out.println(message);
        }
    };
    Trap t;
    Player p;

    @BeforeEach
    void setUp() {
        t = (Trap)tf.produceEnemy('B', new Position(0,0),ms);
        p = tf.producePlayer(1, new Position(0,1),ms);
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