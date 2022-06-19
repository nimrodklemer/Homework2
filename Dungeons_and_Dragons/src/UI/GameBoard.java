package UI;

import business.EmptySpace;
import business.Enemy;
import business.Position;
import business.Tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameBoard {
    private List<Tile> tiles;

    public GameBoard(Tile[][] board){
        tiles = new ArrayList<>();
        for(Tile[] line: board){
            tiles.addAll(Arrays.asList(line));
        }
    }

    public static Tile get(int x, int y) {

        for(Tile t : tiles){
            if (t.getPosition().equals(Position.at(x, y))){
                return t;
            }
        }
        // Throw an exception if no such tile.
        return null;///added
    }

    public static void remove(Enemy e) {
        tiles.remove(e);
        Position p = e.getPosition();
        tiles.add(new EmptySpace(p));
    }

    @Override
    public String toString() {
        return tiles = tiles.stream().sorted().collect(Collectors.toList());
    }
}
