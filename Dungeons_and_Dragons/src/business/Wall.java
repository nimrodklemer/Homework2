package business;

public class Wall extends Tile {
    public Wall(Position p){
        super('#');
        this.position = p;
    }

    @Override
    public void accept(visitor v) {
        v.visit(this);
    }

    public void visit(EmptySpace p){
        return;
    }
    public void visit(Player p){
        return;
    }
    public void visit(Enemy e){
        return;
    }
    public void visit(Wall w){
        return;
    }
}
