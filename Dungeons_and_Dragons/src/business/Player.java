package business;

import UI.MessageCallback;

import java.util.ArrayList;

public abstract class Player extends Unit implements HeroicUnit<Enemy[]> {

    Integer experience;
    Integer playerLevel;
    ArrayList<Enemy> enemies;
    private boolean isAlive = true;
    public Player(String Name, int HealthPool, int Attack, int Defense) {
        super('@', Name, HealthPool, Attack, Defense);
        experience = 0;
        playerLevel = 1;
    }

    public void initialize(Position position, MessageCallback messageCallback, ArrayList<Enemy> enemies){
        this.initialize(position);
        this.messageCallback = messageCallback;
        this.enemies = enemies;
    }

    @Override
    public String describe() {
        return super.describe() + String.format("\t\tExperience: %d\t\tLevel: %d", experience, playerLevel);
    }


    public void castAbility(){}

    @Override
    public void processStep() {

    }


    public void levelUp(){
        if(experience<50*playerLevel)
            return;
        experience-=50*playerLevel;
        playerLevel++;
        healthPool+=healthPool+10*playerLevel;
        healthAmount=healthPool;
        attackPoints+=4*playerLevel;
        defensePoints+=playerLevel;

    }

    @Override
    public void accept(visitor v) {
        v.visit(this);
    }

    public void visit(Player p){
        //To Do
    }
    public void visit(Enemy e){
        this.battle(e);
    }

    public void visit(EmptySpace es){
        this.switchPosition(es);
    }

    public void visit(Wall w){
        // To Do
    }


    public Integer getPlayerLevel() {
        return playerLevel;
    }

    public void addXP(Integer xp){
        setExperience(getExperience() + xp);
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
        messageCallback.print("you picked a fight with " + e.name);
        int attack = (int) Math.floor(this.getAttackPoints() * Math.random());
        int defense = (int) Math.floor(e.getDefensePoints() * Math.random());
        e.takeDamage(attack - defense);
        if(e.getHealth() <= 0){
            messageCallback.print("you killed" + e.name );
            this.addXP(e.getExperienceValue());
            this.switchPosition(e);
            GameBoard.remove(e);
            enemies.remove(e);
        }
    }

    protected void maxAttackBattle(Enemy e){
        messageCallback.print("you bully " + e.name + " with unbalanced skills, what a hero");
        int attack = getAttackPoints();
        int defense = (int) Math.floor(e.getDefensePoints() * Math.random());
        e.takeDamage(attack - defense);
        if(e.getHealth() <= 0){
            messageCallback.print("you killed" + e.name );
            this.addXP(e.getExperienceValue());
            this.switchPosition(e);
            GameBoard.remove(e);
            enemies.remove(e);
        }
    }
    public boolean getIsAlive(){
        return isAlive;
    }

    public void death(){
        this.isAlive = false;
        messageCallback.print("so closed.... :(");
    }


}
