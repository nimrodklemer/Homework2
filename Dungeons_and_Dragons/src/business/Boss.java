package business;

public class Boss extends Monster implements HeroicUnit {

    Integer abilityFrequency;
    Integer combatTicks;

    public Boss(char Tile, String Name, int Health, int Attack, int Defence, int ExperienceValue,int VisionRange, Integer AbilityFrequency) {
        super(Tile, Name, Health, Attack, Defence, ExperienceValue, VisionRange);
        abilityFrequency = AbilityFrequency;
        combatTicks = 0;
    }


    public void castAbility(Player p) {
        this.unFairBattle(p);
    }

    protected void maxAttackBattle(Player p){
        int attack = getAttackPoints();
        int defense = (int) Math.floor(p.getDefensePoints() * Math.random());
        p.takeDamage(attack - defense);
        if(p.getHealth() <= 0){
            // handle death of player.
        }
    }

    public void move(Player p){
        
    }
}
