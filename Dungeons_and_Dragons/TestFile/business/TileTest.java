package business;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    TileFactory tf = new TileFactory();
    MessageCallback ms = new MessageCallback() {
        public void print(String message) {
            System.out.println(message);
        }
    };
    Tile t;

    @BeforeEach
    void setUp() {
        t = tf.produceEmpty(new Position(0,0));
    }

    @Test
    void initialize() {
        t.initialize(new Position(1,1));
        Assertions.assertEquals(true, t.position.equals(new Position(1,1)), "Didn't update tile's position.");
    }


    @Test
    void switchPosition() {
        Tile t2 = tf.produceEmpty(new Position(2,2));
        t.initialize(new Position(1,1));
        t.switchPosition(t2);
        Assertions.assertEquals(true, t.position.equals(new Position(2,2)), "Didn't switch position of calling tile.");
        Assertions.assertEquals(true, t2.position.equals(new Position(1,1)), "Didn't switch position of parameter tile.");
    }
}