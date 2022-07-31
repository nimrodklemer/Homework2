package business;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RogueTest {

    TileFactory tf = new TileFactory();
    MessageCallback ms = new MessageCallback() {
        public void print(String message) {
            System.out.println(message);
        }
    };
    Rogue r;
    Integer cost, currentEnergy, attackPoints, playerLevel;
    @BeforeEach
    void setUp() {
        r = (Rogue)tf.producePlayer(5,new Position(0,0), ms);
        cost = r.cost;
        currentEnergy = r.currentEnergy;
        attackPoints = r.attackPoints;
        r.setExperience(50*r.playerLevel);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void levelUp() {
        r.levelUp();
        Assertions.assertEquals(100, r.currentEnergy, "Didn't update rogue's cost.");
        Assertions.assertEquals(attackPoints+7*r.playerLevel, r.attackPoints, "Didn't update rogue's attack points.");
    }


    @Test
    void describe() {
        String expected = String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", r.getName(), r.getHealth(), r.getAttackPoints(), r.getDefensePoints())
                + String.format("\t\tExperience: %d\t\tLevel: %d", r.experience, r.playerLevel)
                + String.format("\t\tS. Ability Cost: %d\t\tCurrent Energy: %d", r.cost, r.currentEnergy);;
        Assertions.assertEquals(expected, r.describe(), "Doesn't describe right.");
    }


    @Test
    void castAbility() {
        Monster m = (Monster)tf.produceEnemy('s', new Position(0,1),ms);
        Trap t = (Trap)tf.produceEnemy('Q', new Position(0,2),ms);
        r.setPosition(new Position(0,6));
        m.setPosition(new Position(0,0));
        t.setPosition(new Position(0,5));
        int originalPlayerHealth = r.healthAmount;
        int mH = m.getHealth(), tH = t.getHealth();
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(m);
        enemies.add(t);


        //case current energy < cost
        r.currentEnergy = 10;
        currentEnergy = r.currentEnergy;
        r.cost = 20;
        cost = r.currentEnergy;

        r.castAbility(enemies, r);
        //ability on enemy beyond range
        Assertions.assertEquals(mH, m.getHealth(), "Attacked an enemy out of range with ability.");
        //ability on enemy in range
        Assertions.assertEquals(tH, t.getHealth(), "Attacked an enemy in range with ability despite energy < cost.");
        //heal player
        Assertions.assertEquals(currentEnergy, r.currentEnergy, "Lowered current energy despite energy < cost.");

        //case current energy >= cost
        r.currentEnergy = 40;
        currentEnergy = r.currentEnergy;
        r.cost = 20;
        cost = r.currentEnergy;

        r.castAbility(enemies, r);
        //ability on enemy beyond range
        Assertions.assertEquals(mH, m.getHealth(), "Attacked an enemy out of range with ability.");
        //t is in range, so energy should have lowered.
        Assertions.assertEquals(currentEnergy - cost, r.currentEnergy, "Lowered current energy despite energy < cost.");

    }
}