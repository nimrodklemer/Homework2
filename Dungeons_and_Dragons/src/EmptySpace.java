public class EmptySpace extends Tile{
    public EmptySpace(Position p){
        super('.');
        this.position = p;
    }

    @Override
    public void accept(Unit unit) {

    }
}