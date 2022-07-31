package business;

import java.util.ArrayList;

public class Rogue extends Player {

    Integer cost;
    Integer currentEnergy;

    public Rogue(String Name, int HealthPool,int Attack,int Defense, int Cost) {
        super(Name, HealthPool, Attack, Defense);
        this.cost = Cost;
        currentEnergy = 100;
    }

    @Override
    public void processStep() {
        currentEnergy = Math.min(currentEnergy +10 ,100);
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


    public void castAbility(ArrayList<Enemy> enemies, Player p) {
        if(currentEnergy < cost){
            messageCallback.print("not enough energy to activate the broken skill");
        }
        else {
            for (Enemy enemy : enemies) {
                if (this.range(enemy) < 2) {//around him
                    this.maxAttackBattle(enemy);
                    currentEnergy = currentEnergy - cost;
                }
                else{
                    messageCallback.print("no enemy in range for using the broken skill");
                }
            }
        }

    }
}
