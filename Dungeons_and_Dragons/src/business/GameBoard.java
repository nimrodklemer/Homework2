package business;

import business.EmptySpace;
import business.Enemy;
import business.Position;
import business.Tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameBoard {
    private static List<Tile> tiles;
    int Y;
    int X;

    public GameBoard(Tile[][] board, int Y, int X){
        tiles = new ArrayList<>();
        for(Tile[] line: board){
            tiles.addAll(Arrays.asList(line));
        }
        this.Y = Y;
        this.X = X;
    }

    public static Tile get(int x, int y) {
        for(Tile t : tiles){
            if (t.getPosition().equals(Position.at(x, y))){
                return t;
            }
        }
        // Throw an exception if no such tile.
        throw new IndexOutOfBoundsException("there is no such tile");
    }

    public static void remove(Enemy e) {
        tiles.remove(e);
        Position p = e.getPosition();
        TileFactory factory = new TileFactory();
        tiles.add(factory.produceEmpty(p));
        tiles = tiles.stream().sorted().collect(Collectors.toList());
    }


    public String toString() {
        String output = "";
        tiles = tiles.stream().sorted().collect(Collectors.toList());
        for(int i=0; i < Y; i++){
            for(int j=0; j < X; j++){
                output = output + tiles.get(X*i + j).toString();
            }
            output = output + "\n";
        }
        return output;

    }
}
