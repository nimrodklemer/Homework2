package business;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TileFactoryTest {

    TileFactory tf;

    @BeforeEach
    void setUp() {
        TileFactory tf = new TileFactory();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void produceEnemy() {
        // existing enemy - success
        Enemy enemy = tf.produceEnemy('s', new Position(3,3), (message) -> System.out.println(message));
        Assertions.assertEquals(true, enemy!=null, "Didn't create enemy properly");

        // non-existing enemy - fail
        enemy = tf.produceEnemy('u', new Position(3,3), (message) -> System.out.println(message));
        Assertions.assertEquals(true, enemy==null, "Created enemy with non existing key");
    }

    @Test
    void producePlayer() {
        // existing player - success
        Player p = tf.producePlayer(1, new Position(3,3), (message) -> System.out.println(message));
        Assertions.assertEquals(true, p == tf.listPlayers().get(0), "Didn't create right player.");

        // non-existing player - fail
        p = tf.producePlayer(tf.listPlayers().size()+1, new Position(4,3), (message) -> System.out.println(message));
        Assertions.assertEquals(true, p==null, "Created player with out of index id");
    }

    @Test
    void produceEmpty() {
        EmptySpace e = tf.produceEmpty(new Position(3,3));
        Assertions.assertEquals(true, e!=null, "Didn't create empty space properly");
    }

    @Test
    void produceWall() {
        Wall w = tf.produceWall(new Position(3,3));
        Assertions.assertEquals(true, w!=null, "Didn't create wall properly");
    }
}