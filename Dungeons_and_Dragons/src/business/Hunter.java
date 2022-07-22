package business;

import java.util.ArrayList;

public class Hunter extends Player {

    Integer range;
    Integer arrowsCount;
    Integer ticksCount;

    public Hunter(String Name, int HealthPool,int Attack, int Defense, int Range) {
        super(Name, HealthPool, Attack, Defense);
        this.range = Range;
        this.arrowsCount = 10;
        ticksCount = 0;
    }

    public void levelUp() {
        super.levelUp();
        this.arrowsCount = 10 * getPlayerLevel();
        setAttackPoints(getAttackPoints() + 2 * getPlayerLevel());
        setDefensePoints(getDefensePoints() + getPlayerLevel());

    }

    public void castAbility(ArrayList<Enemy> enemies, Player p) {
        if(arrowsCount == 0){
            messageCallback.print("not enough arrows to activate the broken skill");
        }
        else {
            double minRange = -1;
            Enemy closestEnemy = null;
            for (Enemy enemy : enemies) {
                if (minRange == -1 | minRange > this.range(enemy)) {
                    minRange = this.range(enemy);
                    closestEnemy = enemy;
                }
            }
            if (closestEnemy != null) {
                this.maxAttackBattle(closestEnemy);
                setArrowsCount(getArrowsCount() - 1);
            }
            else{
                messageCallback.print("no enemy within range");
            }
        }
    }

    public Integer getArrowsCount() {
        return arrowsCount;
    }

    public void setArrowsCount(Integer arrowsCount) {
        this.arrowsCount = arrowsCount;
    }
}
