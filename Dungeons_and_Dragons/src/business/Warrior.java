package business;

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

    public void castAbility(Enemy[] enemies) {
        for(Enemy enemy:enemies){
            if (this.range(enemy) < 3){
                enemy.takeDamage(this.healthPool/10);
                this.Heal(10 * this.getDefensePoints());
            }
        }
    }
}
