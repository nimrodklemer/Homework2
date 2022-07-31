package business;

import org.junit.jupiter.api.Assertions;

class PlayerTest {

    TileFactory tf = new TileFactory();
    MessageCallback ms = new MessageCallback() {
        public void print(String message) {
            System.out.println(message);
        }
    };
    Player p;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        p = tf.producePlayer(1,new Position(0,0), ms);
    }

    public static boolean testBattle(Player p, Enemy e, int attack, int defense){

        e.takeDamage(attack - defense);
        if(e.getHealth() <= 0){
            p.switchPosition(e);
            e.death();
            p.addXP(e.getExperienceValue());
            return true;
        }
        return false;
    }



    @org.junit.jupiter.api.Test
    void battle() {
        Monster m1 = (Monster)tf.produceEnemy('s', new Position(0,1), ms);
        Monster m2 = (Monster)tf.produceEnemy('s', new Position(0,2), ms);
        Monster m3 = (Monster)tf.produceEnemy('s', new Position(0,3), ms);
        int m1HA = m1.healthAmount, m2HA = m2.healthAmount, m3HA = m3.healthAmount;

        // test to attack, do damage, and not kill
        boolean enemyDied = testBattle(p, m1, 11, 1);
        Assertions.assertEquals(m1HA-10, m1.healthAmount, "Didn't lower enemy health.");
        Assertions.assertEquals(false, enemyDied, "Enemy died despite health above 0.");

        // test to do no damage with attack - defense
        enemyDied = testBattle(p, m2, 30, 30);
        Assertions.assertEquals(m2HA, m2.healthAmount, "Lowered enemy health despite 0 sum battle.");
        Assertions.assertEquals(false, enemyDied, "Enemy died despite no damage.");

    }

}