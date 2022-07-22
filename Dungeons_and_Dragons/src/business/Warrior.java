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
        if(remainingCooldown > 0){
            messageCallback.print("broken skill still on cooldown");
        }
        else {
            for (Enemy enemy : enemies) {
                if (this.range(enemy) < 3) {
                    enemy.takeDamage(this.healthPool / 10);
                    this.Heal(10 * this.getDefensePoints());
                    remainingCooldown = abilityCooldown;
                }
                else{
                    messageCallback.print("no enemy in range for using the broken skill");
                }
            }
        }
    }
}
