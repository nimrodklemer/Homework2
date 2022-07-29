package business;

public class Position implements Comparable<Position>{
    int x, y;

    public Position(int y, int x){
        this.x = x;
        this.y = y;
    }

    public Position(Position other){
        this.x = other.x;
        this.y = other.y;
    }

    public static Position at(int y, int x){
        return new Position(y,x);
    }

    @Override
    public int compareTo(Position p) {
        if(x == p.x && y == p.y)
            return 0;
        if(y < p.y) {
            return -1;
        }
        else if(y == p.y & x < p.x){
            return -1;
        }
        return 1;
    }

    public boolean equals(Position p){
        return x == p.x && y == p.y;
    }
}
