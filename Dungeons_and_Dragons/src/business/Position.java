package business;

public class Position implements Comparable<Position>{
    int x, y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
    public static Position at(int x, int y){
        return new Position(x,y);
    }

    @Override
    public int compareTo(Position p) {
        if(x == p.x && y == p.y)
            return 0;
        if(x < p.x)
            return -1;
        return 1;
    }

    public boolean equals(Position p){
        return x == p.x && y == p.y;
    }
}
