package business;

public abstract class Tile implements Comparable<Tile> ,visited, visitor {
    protected char tile;
    protected Position position;

    protected Tile(char tile){
        this.tile = tile;
    }

    protected void initialize(Position position){
        this.position = position;
    }

    public char getTile() {
        return tile;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void switchPosition(Tile tile){
        Position save = this.getPosition();
        this.setPosition(tile.getPosition());
        tile.setPosition(save);
    }

    public abstract void visit(EmptySpace es);
    public abstract void visit(Wall w);

    public abstract void visit(Player p);
    public abstract void visit(Enemy e);
    public abstract void accept(visitor v);

    @Override
    public int compareTo(Tile tile) {
        return getPosition().compareTo(tile.getPosition());
    }

    @Override
    public String toString() {
        return String.valueOf(tile);
    }
}

