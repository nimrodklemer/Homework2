public class Mage extends Player{

    Integer manaPool;
    Integer currentMana;
    Integer spellPower;
    Integer manaCost;
    Integer hitsCount;
    Integer abilityRange;

    public Mage(String Name, int HealthPool, int Attack,int Defense, int ManaPool, int ManaCost, int SpellPower, int HitsCount, int AbilityRange) {
        super(Name, HealthPool, Attack, Defense);
        currentMana = ManaPool / 4;
        spellPower = SpellPower;
        hitsCount = HitsCount;
        abilityRange = AbilityRange;
        manaCost = ManaCost;
    }


    @Override
    public void levelUp() {
        super.levelUp();
        manaPool+=25*playerLevel;
        int a = currentMana+manaPool/4, b = manaPool;
        if(a < b)
            currentMana=a;
        else
            currentMana=b;
    }

    @Override
    public String describe() {
        return super.describe() + String.format("\t\tMana: %d\t\tMana pool: %d\t\tSpell Power: %d\t\tHits Count: %d\t\tAbility Range: %d",
                currentMana, manaPool, spellPower, hitsCount, abilityRange);
    }

    @Override
    public void castAbility() {
        return;
    }

}
