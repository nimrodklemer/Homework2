package business;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest {

    TileFactory tf = new TileFactory();
    MessageCallback ms = new MessageCallback() {
        public void print(String message) {
            System.out.println(message);
        }
    };

    Unit unitPlayer;
    Tile tileWall;
    Unit unitMonster;
    Tile tileEmpty;
    @BeforeEach
    void setUp() {
        unitPlayer = tf.producePlayer(1,new Position(1,1), ms);
        tileWall = tf.produceWall(new Position(2,1));
        unitMonster = tf.produceEnemy('s',new Position(1,0), ms);
        tileEmpty = tf.produceEmpty(new Position(0,1));

    }
    @Test
    void interact1() {
        unitPlayer.interact(tileEmpty);
        Assertions.assertEquals(true, unitPlayer.getPosition().equals(new Position(0,1)));
        Assertions.assertEquals(true, tileEmpty.getPosition().equals(new Position(1,1)));
    }
    @Test
    void interact2(){
        unitPlayer.interact(tileWall);
        Assertions.assertEquals(true, unitPlayer.getPosition().equals(new Position(1,1)));
        Assertions.assertEquals(true, tileEmpty.getPosition().equals(new Position(2,1)));
    }

    @Test
    void interact3(){
        unitMonster.interact(tileEmpty);
        Assertions.assertEquals(true, unitMonster.getPosition().equals(new Position(0,1)));
        Assertions.assertEquals(true, tileEmpty.getPosition().equals(new Position(1,1)));
    }

    @Test
    void interact4(){
        unitMonster.interact(tileWall);
        Assertions.assertEquals(true, unitMonster.getPosition().equals(new Position(1,1)));
        Assertions.assertEquals(true, tileEmpty.getPosition().equals(new Position(2,1)));
    }
}