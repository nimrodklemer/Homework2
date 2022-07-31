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

    @Override
    public String describe() {
        return super.describe() + String.format("\t\tRange: %d\t\tArrows: %d\t\tticksCount: %d", range, arrowsCount, ticksCount);
    }

    public void levelUp() {
        super.levelUp();
        this.arrowsCount = 10 * getPlayerLevel();
        setAttackPoints(getAttackPoints() + 2 * getPlayerLevel());
        setDefensePoints(getDefensePoints() + getPlayerLevel());

    }

    @Override
    public void processStep() {
        if(ticksCount == 10){
            arrowsCount = arrowsCount + getPlayerLevel();
            ticksCount = 0;
        }
        else{
            ticksCount++;
        }
    }

    public void castAbility(ArrayList<Enemy> enemies, Player p) {
        if(arrowsCount == 0){
            messageCallback.print("not enough arrows to activate the broken skill");
        }
        else {
            int power = getAttackPoints();
            double minRange = range;
            Enemy closestEnemy = null;
            for (Enemy enemy : enemies) {
                if (minRange >= this.range(enemy)) {
                    minRange = this.range(enemy);
                    closestEnemy = enemy;
                }
            }
            if (closestEnemy != null) {
                int defense = (int) Math.floor(closestEnemy.getDefensePoints() * Math.random());
                messageCallback.print("you dealt to " + closestEnemy.getName() + " " + Math.max(0, power - defense) + " with your special ability");
                closestEnemy.takeDamage(power - defense);
                if(closestEnemy.getHealth() == 0){
                    closestEnemy.death();
                    this.addXP(closestEnemy.getExperienceValue());
                }
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
