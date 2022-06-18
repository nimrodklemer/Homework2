package business;

public class Rogue extends Player {

    Integer cost;
    Integer currentEnergy;

    public Rogue(String Name, int HealthPool,int Attack,int Defense, int Cost) {
        super(Name, HealthPool, Attack, Defense);
        this.cost = Cost;
        currentEnergy = 100;
    }



    @Override
    public void levelUp() {
        super.levelUp();
        currentEnergy = 100;
        attackPoints+=3*playerLevel;
    }

    @Override
    public String describe() {
        return super.describe() + String.format("\t\tS. Ability Cost: %d\t\tCurrent Energy: %d", cost, currentEnergy);
    }


    public void castAbility(Enemy[] enemies) {
        for(Enemy enemy:enemies){
            if (this.range(enemy) < 2){//around him
                this.unFairBattle(enemy);
            }
        }
        currentEnergy = currentEnergy - cost;
    }
}
