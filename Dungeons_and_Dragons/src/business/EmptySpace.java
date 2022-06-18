package business;

public class EmptySpace extends Tile {
    public EmptySpace(Position p){
        super('.');
        this.position = p;
    }

    @Override
    public void accept(visitor v) {
        v.visit(this);
    }
    public void visit(EmptySpace p){
        ;
    }
    public void visit(Player p){
        ;
    }
    public void visit(Enemy e){
        ;
    }
    public void visit(Wall w){
        ;
    }
}