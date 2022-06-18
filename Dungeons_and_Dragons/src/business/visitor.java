package business;

public interface visitor {
    public void visit(EmptySpace p);
    public void visit(Enemy e);
    public void visit(Wall w);
    public void visit(Player p);
}
