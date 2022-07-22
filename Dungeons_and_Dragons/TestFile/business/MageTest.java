package business;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MageTest {

    Mage m;
    Integer mp, mc, sp, hc, ar, cm, playerLevel;
    @BeforeEach
    void setUp() {
        m = new Mage("Melisandre", 100, 5, 1, 300, 30, 15, 5, 6);
        mp = m.manaPool;
        mc = m.manaCost;
        sp = m.spellPower;
        hc = m.hitsCount;
        ar = m.abilityRange;
        playerLevel = m.playerLevel;
        cm = m.currentMana;
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void levelUp() {
        m.levelUp();
        Assertions.assertEquals(mp+25*playerLevel, m.manaPool, "Didn't update mage's mana pool.");
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
        Monster mm = new Monster('s', "Lannister Solider", 80, 8, 3,25, 3);
        Trap t = new Trap('Q', "Queen's business.Trap", 250, 50, 10, 100, 3, 10);
        m.setPosition(new Position(0,6));
        m.setPosition(new Position(0,0));
        t.setPosition(new Position(0,5));
        int originalPlayerHealth = m.healthAmount;
        int mmH = mm.getHealth(), tH = t.getHealth();
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(mm);
        enemies.add(t);


        //case current mana < mana pool
        m.currentMana = 10;
        cm = m.currentMana;
        m.manaPool = 20;
        mp = m.manaPool;

        m.castAbility(enemies, m);
        //ability on enemy beyond range - checks if mana was used.
        Assertions.assertEquals(cm, m.currentMana, "Attacked an enemy out of range with ability.");
        //ability on enemy within range - checks if mana was used.
        Assertions.assertEquals(cm, m.currentMana, "Attacked an enemy in range with ability despite current mana < mana pool.");

        //case current mana >= mana pool
        m.currentMana = 20;
        cm = m.currentMana;
        m.manaPool = 10;
        mp = m.manaPool;

        m.castAbility(enemies, m);
        //ability on enemy beyond range - checks if mana was used.
        Assertions.assertEquals(cm, m.currentMana, "Attacked an enemy out of range with ability.");
        //ability on enemy within range - checks if mana was used.
        Assertions.assertEquals(cm - mc, m.currentMana, "Didn't attack an enemy in range with ability.");
    }
}