package business;

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

    public void castAbility(Enemy[] enemies) {
        double minRange = -1;
        Enemy closestEnemy = null;
        for(Enemy enemy:enemies){
            if(minRange == -1 & minRange > this.range(enemy)) {
                minRange = this.range(enemy);
                closestEnemy = enemy;
            }
        }
        if(closestEnemy != null) {
            this.unFairBattle(closestEnemy);
        }
        setArrowsCount(getArrowsCount() - 1);
    }

    public Integer getArrowsCount() {
        return arrowsCount;
    }

    public void setArrowsCount(Integer arrowsCount) {
        this.arrowsCount = arrowsCount;
    }
}
