package business;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HunterTest {

    Hunter h;
    Integer ha, ac, ap, dp;
    @BeforeEach
    void setUp() {
        h = new Hunter("Ygritte", 220, 30, 2, 6);
        ha = h.healthAmount;
        ac = h.arrowsCount;
        ap = h.attackPoints;
        dp = h.defensePoints;
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void levelUp() {
        h.levelUp();
        Assertions.assertEquals(10*h.playerLevel, h.arrowsCount, "Didn't update hunter's arrow count.");
        Assertions.assertEquals(ap+2*h.playerLevel, h.attackPoints, "Didn't update hunter's attack points.");
        Assertions.assertEquals(dp+1*h.playerLevel, h.defensePoints, "Didn't update hunter's defense points.");
    }

    @Test
    void castAbility() {
        Monster m = new Monster('s', "Lannister Solider", 80, 8, 3,25, 3);
        Trap t = new Trap('Q', "Queen's business.Trap", 250, 50, 10, 100, 3, 10);
        h.setPosition(new Position(0,6));
        m.setPosition(new Position(0,0));
        t.setPosition(new Position(0,5));
        int mH = m.getHealth(), tH = t.getHealth();
        ArrayList<Enemy> enemies = new ArrayList<>();
        ArrayList<Enemy> enemies2 = new ArrayList<>();

        enemies.add(m);
        enemies.add(t);


        //case arrow count = 0
        h.arrowsCount = 0;

        h.castAbility(enemies, h);
        //ability on enemy not closest
        Assertions.assertEquals(true, m.getHealth() == mH, "Attacked an enemy not closest with ability.");
        //ability closest enemy - checks the use of an arrow.
        Assertions.assertEquals(ac, h.arrowsCount, "Attacked closest enemy with ability despite no arrows.");

        //case arrow count > 0
        h.arrowsCount = 10;

        h.castAbility(enemies, h);
        //ability on enemy not closest
        Assertions.assertEquals(true, m.getHealth() == mH, "Attacked an enemy not closest with ability.");
        //ability closest enemy - checks the use of an arrow.
        Assertions.assertEquals(ac-1, h.arrowsCount, "Didn't attack closest enemy with ability.");

        //case no closest enemy exists

        h.castAbility(enemies2, h);
        //checks the use of an arrow.
        Assertions.assertEquals(ac-1, h.arrowsCount, "Used an arrow without an enemy to attack with ability.");

    }
}