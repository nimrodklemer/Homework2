package business;

public abstract class Enemy extends Unit {
    public Integer experienceValue;
    public Enemy(char Tile, String Name, int Health, int Attack, int Defence, int ExperienceValue) {
        super(Tile, Name, Health, Attack, Defence);
        experienceValue = ExperienceValue;
    }

    @Override
    public void processStep() {
        //To Do
    }


    public void visit(Player p){
        this.battle(p);
    }
    public void visit(Enemy e){
    }
    public void visit(EmptySpace es){
        this.switchPosition(es);
    }

    public void visit(Wall w){
    }

    @Override
    public void accept(visitor v) {
        v.visit(this);
    }

    public Integer getExperienceValue() {
        return experienceValue;
    }

    protected void battle(Player p){
        int attack = (int) Math.floor(this.getAttackPoints() * Math.random());
        int defense = (int) Math.floor(p.getDefensePoints() * Math.random());
        p.takeDamage(attack - defense);
        if(p.getHealth() <= 0){
            p.death();
        }
    }

    public void death(){
        GameBoard.remove(this);
    }

    public abstract void move(Player player);
}
