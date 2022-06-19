package business;

import java.lang.Math;
import javax.swing.text.Position;

import static business.Position.at;

public abstract class Unit extends Tile{

    public String name;
    public Integer healthPool;
    public Integer healthAmount;
    public Integer attackPoints;
    public Integer defensePoints;

    protected Unit(char Tile, String Name, int HealthPool, int Attack, int Defense) {
        super(Tile);
        this.name = Name;
        this.healthPool = HealthPool;
        this.attackPoints = Attack;
        this.defensePoints = Defense;
    }

    protected void initialize(Position position, MessageCallback messageCallback){
        //
    }

    protected int attack(){
		return 0;
    }

    public int defend(){
        return 0;
    }

    public double range(Unit other){
        int  x1 = this.getPosition().x;
        int  x2 = other.getPosition().x;
        int  y1 = this.getPosition().y;
        int  y2 = other.getPosition().y;

        return Math.sqrt(Math.pow(x1-x2,2) - Math.pow(y1-y2,2));
    }

    // Should be automatically called once the unit finishes its turn
    public abstract void processStep();


    // This unit attempts to interact with another tile.
    public void interact(Tile tile){
		tile.accept(this);
    }
    public void move(){}


    // Combat against another unit.






    public String describe() {
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", getName(), getHealth(), getAttackPoints(), getDefensePoints());
    }

    protected int getDefensePoints() {
        return defensePoints;
    }

    protected int getAttackPoints() {
        return attackPoints;
    }

    protected int getHealth() {
        return healthAmount;
    }

    protected int getMaxHealth() { return healthPool;}

    protected void setAttackPoints(Integer attackPoints) {
        this.attackPoints = attackPoints;
    }

    protected void setDefensePoints(Integer defensePoints) {
        this.defensePoints = defensePoints;
    }


    protected void setHealth(int newHealth) {
        healthAmount = newHealth;
        if(getHealth() > getMaxHealth()){
            healthAmount = getMaxHealth();
        }
    }

    public String getName(){
        return name;
    }

    protected void takeDamage(int Damage) {
        if(Damage>0){
            healthAmount = healthAmount - Damage;
        }
    }

    protected void Heal(int addHealth) {
        setHealth(healthAmount + addHealth);
    }
    protected void moveUp(){
        Tile other = getTile(at(this.getPosition().x, this.getPosition().y + 1));
        this.interact(other);

    }
    protected void moveLeft(){
        Tile other = getTile(at(this.getPosition().x - 1, this.getPosition().y));
        this.interact(other);

    }
    protected void moveRight(){
        Tile other = getTile(at(this.getPosition().x + 1, this.getPosition().y));
        this.interact(other);

    }
    protected void moveDown(){
        Tile other = getTile(at(this.getPosition().x, this.getPosition().y - 1));
        this.interact(other);

    }

}
