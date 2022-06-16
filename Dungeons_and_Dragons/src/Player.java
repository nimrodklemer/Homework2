public class Player extends Unit implements HeroicUnit{

    Integer experience;
    Integer playerLevel;
    public Player(String Name, int HealthPool, int Attack, int Defense) {
        super('@', Name, HealthPool, Attack, Defense);
        experience = 0;
        playerLevel = 1;
    }

    @Override
    public String describe() {
        return super.describe() + String.format("\t\tExperience: %d\t\tLevel: %d", experience, playerLevel);
    }


    @Override
    public void castAbility() {
        return;
    }

    @Override
    public void processStep() {

    }

    @Override
    public void onDeath() {

    }

    public void visit(Player p){
        return;
    }
    public void visit(Enemy e){
        //
    }

    public void levelUp(){
        if(experience<50*playerLevel)
            return;
        playerLevel++;
        healthPool+=healthPool+10*playerLevel;
        healthAmount=healthPool;
        attackPoints+=4*playerLevel;
        defensePoints+=1*playerLevel;

    }

    @Override
    public void accept(Unit unit) {

    }
}
