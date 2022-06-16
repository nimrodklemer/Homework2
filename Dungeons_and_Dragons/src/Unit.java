import javax.swing.text.Position;

public abstract class Unit extends Tile {

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

    // Should be automatically called once the unit finishes its turn
    public abstract void processStep();

    // What happens when the unit dies
    public abstract void onDeath();

    // This unit attempts to interact with another tile.
    public void interact(Tile tile){
		//
    }

    public void visit(EmptySpace e){
		//
    }

    public void visit(Wall w){
        //
    }

    public abstract void visit(Player p);
    public abstract void visit(Enemy e);

    // Combat against another unit.
    protected void battle(Unit u){
        //
    }


    public String describe() {
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", getName(), getHealth(), getAttack(), getDefense());
    }

    protected int getDefense() {
        return defensePoints;
    }

    protected int getAttack() {
        return attackPoints;
    }

    protected int getHealth() {
        return healthAmount;
    }


    public String getName(){
        return name;
    }


}
