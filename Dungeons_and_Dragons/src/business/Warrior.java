package business;

import java.util.ArrayList;

public class Warrior extends Player {

    Integer remainingCooldown;
    Integer abilityCooldown;

    public Warrior(String Name, int HealthPool,int Attack,int Defense, int AbilityCoolDown) {
        super(Name, HealthPool, Attack, Defense);
        abilityCooldown = AbilityCoolDown;
        remainingCooldown = 0;
    }

    @Override
    public void processStep() {
        remainingCooldown = Math.max(0,remainingCooldown -1);
    }

    @Override
    public String describe() {
        return super.describe() + String.format("\t\tRemaining Cooldown: %d\t\tAbility Cooldown: %d", remainingCooldown, abilityCooldown);
    }

    @Override
    public void levelUp() {
        super.levelUp();
        remainingCooldown = 0;
        healthPool+=5*playerLevel;
        attackPoints+=2*playerLevel;
        defensePoints+=1*playerLevel;
    }

    public void castAbility(ArrayList<Enemy> enemies, Player p) {
        boolean nobody = true;
        if(remainingCooldown > 0){
            messageCallback.print("broken skill still on cooldown");
        }
        else {
            for (Enemy enemy : enemies) {
                if (this.range(enemy) < 3) {
                    messageCallback.print("you dealt to " + enemy.getName() + " " + (this.healthPool / 10) + " with your special ability");
                    enemy.takeDamage(this.healthPool / 10);
                    this.Heal(10 * this.getDefensePoints());
                    messageCallback.print(this.name + " (you) healed for " + (10 * this.getDefensePoints()) );
                    if(enemy.getHealth() == 0){
                        enemy.death();
                        this.addXP(enemy.getExperienceValue());
                    }
                    remainingCooldown = abilityCooldown;
                    nobody = false;// there is enemy in range
                }
            }
            if(nobody){
                messageCallback.print("no enemy in range for using the broken skill");
            }
        }
    }
}
