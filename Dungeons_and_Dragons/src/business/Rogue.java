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
            int power = getAttackPoints();
            boolean enemyInRange = false;
            for (Enemy enemy : enemies) {
                if (this.range(enemy) < 2) {//around him
                    enemyInRange = true;
                    int defense = (int) Math.floor(enemy.getDefensePoints() * Math.random());
                    messageCallback.print("you dealt to " + enemy.getName() + " " + Math.max(0, power - defense) + " with your special ability");
                    enemy.takeDamage(power - defense);
                    if(enemy.getHealth() == 0){
                        enemy.death();
                        this.addXP(enemy.getExperienceValue());
                    }
                    currentEnergy = currentEnergy - cost;
                }
            }
            if(!enemyInRange){
                messageCallback.print("no enemy in range for using the broken skill");
            }
        }

    }
}
