package business;

import java.lang.Math;

public abstract class Unit extends Tile{

    public String name;
    public Integer healthPool;
    public Integer healthAmount;
    public Integer attackPoints;
    public Integer defensePoints;
    public MessageCallback messageCallback;


    protected Unit(char Tile, String Name, int HealthPool, int Attack, int Defense) {
        super(Tile);
        this.name = Name;
        this.healthPool = HealthPool;
        this.healthAmount = HealthPool;
        this.attackPoints = Attack;
        this.defensePoints = Defense;
    }

    public void initialize(Position position, MessageCallback messageCallback){
        this.initialize(position);
        this.messageCallback = messageCallback;
    }

    public double range(Unit other){
        int  x1 = this.getPosition().x;
        int  x2 = other.getPosition().x;
        int  y1 = this.getPosition().y;
        int  y2 = other.getPosition().y;

        return Math.sqrt(Math.pow(x1-x2,2) + Math.pow(y1-y2,2));
    }

    // Should be automatically called once the unit finishes its turn
    public abstract void processStep();


    // This unit attempts to interact with another tile.
    public void interact(Tile tile){
		tile.accept(this);
    }



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

    public int getHealth() {
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
        if(healthAmount <=0){
            healthAmount = 0;
        }
    }

    abstract void death();

    protected void Heal(int addHealth) {
        setHealth(healthAmount + addHealth);
    }
    public void moveUp(){
        Tile other = GameBoard.get(this.getPosition().y - 1, this.getPosition().x);
        this.interact(other);

    }
    public void moveLeft(){
        Tile other = GameBoard.get(this.getPosition().y, this.getPosition().x - 1);
        this.interact(other);

    }
    public void moveRight(){
        Tile other = GameBoard.get(this.getPosition().y , this.getPosition().x + 1);
        this.interact(other);

    }
    public void moveDown(){
        Tile other = GameBoard.get(this.getPosition().y + 1, this.getPosition().x);
        this.interact(other);

    }

    public void print(String message){
        messageCallback.print(message);
    }

    public void setMessageCallback(MessageCallback messageCallback) {
        this.messageCallback = messageCallback;
    }
}
