package business;

public abstract class Player extends Unit implements HeroicUnit {

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
    public void castAbility(){}

    @Override
    public void processStep() {

    }

    @Override
    public void onDeath() {

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
    public void accept(visitor v) {
        v.visit(this);
    }

    public void visit(Player p){
        ;
    }
    public void visit(Enemy e){
        this.battle(e);
    }

    public void visit(EmptySpace es){
        this.switchPosition(es);
    }

    public void visit(Wall w){
        ;
    }


    public Integer getPlayerLevel() {
        return playerLevel;
    }

    public void addXP(Integer xp){
        setExperience(getExperience() + xp);
        while(getExperience() >= getPlayerLevel()*50){
            levelUp();
        }
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getExperience() {
        return experience;
    }

    @Override
    public void move() {
        ;
    }

    protected void battle(Enemy e){
        int attack = (int) Math.floor(this.getAttackPoints() * Math.random());
        int defense = (int) Math.floor(e.getDefensePoints() * Math.random());
        e.takeDamage(attack - defense);
        if(e.getHealth() <= 0){
            this.addXP(e.getExperienceValue());
            this.switchPosition(e);
            e.remove();
        }
    }

    protected void maxAttackBattle(Enemy e){
        int attack = getAttackPoints();
        int defense = (int) Math.floor(e.getDefensePoints() * Math.random());
        e.takeDamage(attack - defense);
        if(e.getHealth() <= 0){
            this.addXP(e.getExperienceValue());
            this.switchPosition(e);
            e.remove();
        }
    }

    public void death(){
        if(){

        }
    }
}
