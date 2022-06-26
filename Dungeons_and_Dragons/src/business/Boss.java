package business;

public class Boss extends Monster implements HeroicUnit<Player> {

    Integer abilityFrequency;
    Integer combatTicks;

    public Boss(char Tile, String Name, int Health, int Attack, int Defence, int ExperienceValue,int VisionRange, Integer AbilityFrequency) {
        super(Tile, Name, Health, Attack, Defence, ExperienceValue, VisionRange);
        abilityFrequency = AbilityFrequency;
        combatTicks = 0;
    }


    public void castAbility(Player p) {
        int attack = getAttackPoints();
        int defense = (int) Math.floor(p.getDefensePoints() * Math.random());
        p.takeDamage(attack - defense);
        if(p.getHealth() <= 0){
            p.death();
        }
    }

    public void move(Player p){
        int dx, dy;
        if(this.range(p) < visionRange){
            if(combatTicks.equals(abilityFrequency)){
                combatTicks = 0;
                this.castAbility(p);
            }
            else{
                combatTicks+=1;
                dx = this.position.x - p.position.x;
                dy = this.position.y - p.position.y;
                if(Math.abs(dx) > Math.abs(dy)){
                    if(dx > 0)
                        this.moveLeft();
                    else
                        this.moveRight();
                }
                else{
                    if(dy > 0)
                        this.moveUp();
                    else
                        this.moveDown();
                }
            }
        }
        else{
            combatTicks = 0;
            int direction = (int) (Math.random()*3);
            switch (direction) {
                case 0 -> this.moveLeft();
                case 1 -> this.moveRight();
                case 2 -> this.moveUp();
                case 3 -> this.moveDown();
            }
        }
        
    }
}
