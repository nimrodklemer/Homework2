public class Enemy extends Unit{
    public Integer experienceValue;
    public Enemy(char Tile, String Name, int Health, int Attack, int Defence, int ExperienceValue) {
        super(Tile, Name, Health, Attack, Defence);
        experienceValue = ExperienceValue;
    }

    @Override
    public void processStep() {

    }

    @Override
    public void onDeath() {

    }

    public void visit(Player p){
        //
    }
    public void visit(Enemy e){
        //
    }

    @Override
    public void accept(Unit unit) {

    }
}
