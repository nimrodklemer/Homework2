package business;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class MageTest {

    TileFactory tf = new TileFactory();
    MessageCallback ms = new MessageCallback() {
        public void print(String message) {
            System.out.println(message);
        }
    };
    Mage m;
    Integer mp, mc, sp, hc, ar, cm, playerLevel;
    @BeforeEach
    void setUp() {
        m = (Mage)tf.producePlayer(3, new Position(0,0),ms);
        mp = m.manaPool;
        mc = m.manaCost;
        sp = m.spellPower;
        hc = m.hitsCount;
        ar = m.abilityRange;
        playerLevel = m.playerLevel;
        cm = m.currentMana;
        m.setExperience(50*playerLevel);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void levelUp() {
        m.levelUp();
        Assertions.assertEquals(mp+25*m.playerLevel, m.manaPool, "Didn't update mage's mana pool.");
        mp = m.manaPool;
        int a = cm+mp/4, b = mp;
        Assertions.assertEquals(Math.min(a, b), m.currentMana, "Didn't update mage's current mana.");
    }

    @Test
    void describe() {
        String expected = String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", m.getName(), m.getHealth(), m.getAttackPoints(), m.getDefensePoints())
                + String.format("\t\tExperience: %d\t\tLevel: %d", m.experience, m.playerLevel)
                + String.format("\t\tMana: %d\t\tMana pool: %d\t\tSpell Power: %d\t\tHits Count: %d\t\tAbility Range: %d",
                m.currentMana, m.manaPool, m.spellPower, m.hitsCount, m.abilityRange);
        Assertions.assertEquals(expected, m.describe(), "Doesn't describe right.");
    }

    @Test
    void castAbility() {
        Monster mm = (Monster)tf.produceEnemy('s', new Position(1,1), ms);
        Trap t = (Trap)tf.produceEnemy('Q', new Position(2,2), ms);
        m.setPosition(new Position(0,6));
        mm.setPosition(new Position(0,0));
        t.setPosition(new Position(0,5));
        int originalPlayerHealth = m.healthAmount;
        int mmH = mm.getHealth(), tH = t.getHealth();
        ArrayList<Enemy> enemies1 = new ArrayList<>();
        ArrayList<Enemy> enemies2 = new ArrayList<>();
        enemies1.add(mm);
        enemies2.add(t);


        m.currentMana = 30;
        cm = m.currentMana;
        m.manaCost = 20;
        mp = m.manaCost;

        m.castAbility(enemies1, m);
        //ability on enemy beyond range - checks if mana was used.
        Assertions.assertEquals(cm, m.currentMana, "Attacked an enemy out of range with ability.");

        m.currentMana = 30;
        cm = m.currentMana;
        m.manaCost = 20;
        mc = m.manaCost;

        m.castAbility(enemies2, m);
        //ability on enemy within range - checks if mana was used.
        Assertions.assertEquals(cm-mc, m.currentMana, "Attacked an enemy in range with ability despite current mana < mana pool.");

    }
}