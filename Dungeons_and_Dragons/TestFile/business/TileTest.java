package business;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    Tile t;

    @BeforeEach
    void setUp() {
        t = new EmptySpace();
    }

    @Test
    void initialize() {
        t.initialize(new Position(1,1));
        Assertions.assertEquals(true, t.position.equals(new Position(1,1)), "Didn't update tile's position.");
    }


    @Test
    void switchPosition() {
        Tile t2 = new EmptySpace();
        t.initialize(new Position(1,1));
        t2.initialize(new Position(2,2));
        t.switchPosition(t2);
        Assertions.assertEquals(true, t.position.equals(new Position(2,2)), "Didn't switch position of calling tile.");
        Assertions.assertEquals(true, t2.position.equals(new Position(1,1)), "Didn't switch position of parameter tile.");
    }
}