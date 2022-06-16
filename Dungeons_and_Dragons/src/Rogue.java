public class Rogue extends Player{

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

    @Override
    public void castAbility() {
        return;
    }
}
