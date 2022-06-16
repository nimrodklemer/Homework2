public class Boss extends Monster implements HeroicUnit{

    Integer abilityFrequency;
    Integer combatTicks;

    public Boss(char Tile, String Name, int Health, int Attack, int Defence, int ExperienceValue,int VisionRange, Integer AbilityFrequency) {
        super(Tile, Name, Health, Attack, Defence, ExperienceValue, VisionRange);
        abilityFrequency = AbilityFrequency;
        combatTicks = 0;
    }

    @Override
    public void castAbility() {
        return;
    }
}
