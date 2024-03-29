package business;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class WarriorTest {

    TileFactory tf = new TileFactory();
    MessageCallback ms = new MessageCallback() {
        public void print(String message) {
            System.out.println(message);
        }
    };
    Warrior w;
    int rc, hp, ap, dp, pl;
    @BeforeEach
    void setUp() {
        w = (Warrior)tf.producePlayer(1, new Position(0,6), ms);
        w.setExperience(50);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void describe() {
        String expected = String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", w.getName(), w.getHealth(), w.getAttackPoints(), w.getDefensePoints())
        + String.format("\t\tExperience: %d\t\tLevel: %d", w.experience, w.playerLevel)
        + String.format("\t\tRemaining Cooldown: %d\t\tAbility Cooldown: %d", w.remainingCooldown, w.abilityCooldown);
        Assertions.assertEquals(expected, w.describe(), "Doesn't describe right.");
    }

    @Test
    void levelUp() {
        pl = w.playerLevel;
        rc = w.remainingCooldown;
        hp = w.healthPool;
        ap = w.attackPoints;
        dp = w.defensePoints;
        w.levelUp();
        Assertions.assertEquals(dp+2*w.playerLevel, w.defensePoints, "Didn't update defense points.");
        Assertions.assertEquals(ap+6*w.playerLevel, w.attackPoints, "Didn't update attack points.");
        Assertions.assertEquals(hp+15*w.playerLevel, w.healthPool, "Didn't update health pool.");
        Assertions.assertEquals(rc, 0, "Didn't remaining cooldown.");
    }

    @Test
    void castAbility() {
        Monster m = (Monster)tf.produceEnemy('s', new Position(0,0), ms);
        Trap t = (Trap)tf.produceEnemy('Q', new Position(0,5), ms);
        w.setPosition(new Position(0,6));
        m.setPosition(new Position(0,0));
        t.setPosition(new Position(0,5));
        int originalPlayerHealth = w.healthAmount;
        int mH = m.getHealth(), tH = t.getHealth();
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(m);
        enemies.add(t);


        //case remainingCooldown > 0
        w.remainingCooldown = 3;

        w.castAbility(enemies, w);
        //ability on enemy beyond range
        Assertions.assertEquals(mH, m.getHealth(), "Attacked an enemy out of range with ability.");
        //ability on enemy in range
        Assertions.assertEquals(tH, t.getHealth(), "Attacked an enemy in range with ability despite cooldown.");
        //heal player
        Assertions.assertEquals(originalPlayerHealth, w.healthPool, "Healed warrior despite cooldown.");

        //case remainingCooldown = 0
        w.remainingCooldown = 0;

        //to test ability's healing - it heals 10*w.getDefensePoints().
        w.healthAmount -= 10*w.getDefensePoints();

        w.castAbility(enemies, w);
        //ability on enemy beyond range
        Assertions.assertEquals(mH, m.getHealth(), "Attacked a monster out of range with ability.");
        //ability on enemy in range
        Assertions.assertEquals(tH-w.healthPool/10, t.getHealth(), "Didn't attack a monster in range with ability.");
        //heal player
        Assertions.assertEquals(originalPlayerHealth, w.healthPool, "Didn't heal warrior.");

    }
}