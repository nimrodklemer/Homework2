package business;

import org.junit.jupiter.api.Assertions;

class PlayerTest {
    Player p;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        p = new Warrior("Jon Snow", 300, 30, 4, 3);
    }

    @org.junit.jupiter.api.Test
    void levelUp() {
        int pl = p.playerLevel, hp = p.healthPool, ap = p.attackPoints, dp = p.defensePoints, ha = p.healthAmount;
        p.setExperience(0);
        // expected failure - not enough exp.
        p.levelUp();
        Assertions.assertEquals(p.experience, p.experience, "Didn't update player experience.");
        Assertions.assertEquals(pl, p.playerLevel, "Didn't update player level.");
        Assertions.assertEquals(hp, p.healthPool, "Didn't update player health pool.");
        Assertions.assertEquals(ha, p.healthAmount, "Didn't update player health amount.");
        Assertions.assertEquals(ap, p.attackPoints, "Didn't update player attack points.");
        Assertions.assertEquals(dp, p.defensePoints, "Didn't update player defense points.");

        // Success cases checked in child classes.
    }

    public static boolean testBattle(Player p, Enemy e, int attack, int defense){

        e.takeDamage(attack - defense);
        if(e.getHealth() <= 0){
            p.addXP(e.getExperienceValue());
            p.switchPosition(e);
            e.death();
            return true;
        }
        return false;
    }

    public static boolean testMaxAttackBattle(Player p, Enemy e, int attack, int defense){
        e.takeDamage(attack - defense);
        if(e.getHealth() <= 0){
            p.addXP(e.getExperienceValue());
            p.switchPosition(e);
            e.death();
            e.DeathCall.call();
            return true;
        }
        return false;
    }


    @org.junit.jupiter.api.Test
    void battle() {
        Monster m1 = new Monster('s', "Lannister Solider1", 80, 8, 3,25, 3);
        Monster m2 = new Monster('s', "Lannister Solider2", 80, 8, 30,25, 3);
        Monster m3 = new Monster('s', "Lannister Solider3", 80, 8, 40,25, 3);
        int m1HA = m1.healthAmount, m2HA = m2.healthAmount, m3HA = m3.healthAmount;

        // test to attack, do damage, and not kill
        boolean enemyDied = testBattle(p, m1, 11, 1);
        Assertions.assertEquals(m1HA-10, m1.healthAmount, "Didn't lower enemy health.");
        Assertions.assertEquals(false, enemyDied, "Enemy died despite health above 0.");

        // test to do no damage with attack - defense
        enemyDied = testBattle(p, m2, 30, 30);
        Assertions.assertEquals(m2HA, m2.healthAmount, "Lowered enemy health despite 0 sum battle.");
        Assertions.assertEquals(false, enemyDied, "Enemy died despite no damage.");

        // test to do no damage with attack < defense
        enemyDied = testBattle(p, m3, 30, 40);
        Assertions.assertEquals(m3HA, m3.healthAmount, "Lowered enemy health despite defense stronger.");
        Assertions.assertEquals(false, enemyDied, "Enemy died despite no damage.");

        // test to attack and kill
        enemyDied = testBattle(p, m3, 90, 10);
        Assertions.assertEquals(0, m3.healthAmount, "Didn't lower enemy health despite defense stronger.");
        Assertions.assertEquals(true, enemyDied, "Enemy didn't die despite zero health.");
    }

    @org.junit.jupiter.api.Test
    void maxAttackBattle() {
        Monster m1 = new Monster('s', "Lannister Solider1", 80, 8, 3,25, 3);
        Monster m2 = new Monster('s', "Lannister Solider2", 80, 8, 30,25, 3);
        Monster m3 = new Monster('s', "Lannister Solider3", 80, 8, 40,25, 3);
        int m1HA = m1.healthAmount, m2HA = m2.healthAmount, m3HA = m3.healthAmount;

        // test to attack, do damage, and not kill
        boolean enemyDied = testMaxAttackBattle(p, m1, 11, 1);
        Assertions.assertEquals(m1HA-10, m1.healthAmount, "Didn't lower enemy health.");
        Assertions.assertEquals(false, enemyDied, "Enemy died despite health above 0.");

        // test to do no damage with attack - defense
        enemyDied = testMaxAttackBattle(p, m2, 30, 30);
        Assertions.assertEquals(m2HA, m2.healthAmount, "Lowered enemy health despite 0 sum battle.");
        Assertions.assertEquals(false, enemyDied, "Enemy died despite no damage.");

        // test to do no damage with attack < defense
        enemyDied = testMaxAttackBattle(p, m3, 30, 40);
        Assertions.assertEquals(m3HA, m3.healthAmount, "Lowered enemy health despite defense stronger.");
        Assertions.assertEquals(false, enemyDied, "Enemy died despite no damage.");

        // test to attack and kill
        enemyDied = testMaxAttackBattle(p, m3, 90, 10);
        Assertions.assertEquals(0, m3.healthAmount, "Didn't lower enemy health despite defense stronger.");
        Assertions.assertEquals(true, enemyDied, "Enemy didn't die despite zero health.");
    }

}