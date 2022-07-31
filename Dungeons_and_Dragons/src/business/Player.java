package business;

import java.util.ArrayList;

public abstract class Player extends Unit implements HeroicUnit{

    Integer experience;
    Integer playerLevel;
    private boolean isAlive = true;
    public Player(String Name, int HealthPool, int Attack, int Defense) {
        super('@', Name, HealthPool, Attack, Defense);
        experience = 0;
        playerLevel = 1;
    }


    @Override
    public String describe() {
        return super.describe() + String.format("\t\tExperience: %d\t\tLevel: %d", experience, playerLevel);
    }


    public abstract void castAbility(ArrayList<Enemy> e, Player p);

    @Override
    public void processStep() {

    }


    public void levelUp(){
        if(experience<50*playerLevel)
            return;
        experience-=50*playerLevel;
        playerLevel++;
        healthPool=healthPool+10*playerLevel;
        healthAmount=healthPool;
        attackPoints+=4*playerLevel;
        defensePoints+=playerLevel;

    }

    @Override
    public void accept(visitor v) {
        v.visit(this);
    }

    public void visit(Player p){
        //Do nothing.
    }
    public void visit(Enemy e){
        this.battle(e);
    }

    public void visit(EmptySpace es){
        this.switchPosition(es);
    }

    public void visit(Wall w){
        //Do nothing.
    }


    public Integer getPlayerLevel() {
        return playerLevel;
    }

    public void addXP(Integer xp){
        setExperience(getExperience() + xp);
        messageCallback.print("you gained " + xp + " experience" );
        while(getExperience() >= getPlayerLevel()*50){
            levelUp();
            messageCallback.print(name + " reached level " + this.getPlayerLevel());
        }
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getExperience() {
        return experience;
    }


    protected void battle(Enemy e){
        int attack = (int) Math.floor(this.getAttackPoints() * Math.random());
        int defense = (int) Math.floor(e.getDefensePoints() * Math.random());
        messageCallback.print("You, " + name + ", picked a fight with "+ e.getName()+". " +name + "'s (you) Attack Roll " + attack + ", " +e.getName()  +"'s (Enemy) Defence Roll " +defense + ", "+ e.getName()+ " (enemy) took " + Math.max((attack - defense),0) + " damage"   );
        e.takeDamage(attack - defense);
        if(e.getHealth() == 0){
            this.switchPosition(e);
            e.death();
            this.addXP(e.getExperienceValue());
        }
    }

    public boolean getIsAlive(){
        return isAlive;
    }

    public void death(){
        this.isAlive = false;
        messageCallback.print("oh you died :)");
    }

    @Override
    public String toString(){
        if(getIsAlive()){
            return String.valueOf(tile);
        }
        else{
            return "X";
        }
    }


}
