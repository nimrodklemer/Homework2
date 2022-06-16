public class Warrior extends Player{

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

    @Override
    public void castAbility() {
        return;
    }
}
