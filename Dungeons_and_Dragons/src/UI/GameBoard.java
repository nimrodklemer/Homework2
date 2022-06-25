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
        throw new IndexOutOfBoundsException("there is no such tile");
    }

<<<<<<< HEAD
    public Enemy[] getEnemies(){
        for(int i=0; i<tiles.size(); i++){
            if
        }
    }
    public void remove(Enemy e) {
=======
    public static void remove(Enemy e) {
>>>>>>> 60c2cee9d3d16546f8a1fd5981791c7d92d4bca1
        tiles.remove(e);
        Position p = e.getPosition();
        tiles.add(new EmptySpace(p));
    }

    @Override
    public String toString() {
<<<<<<< HEAD
        String output = "";
        tiles = tiles.stream().sorted().collect(Collectors.toList());
        for(int i=0; i < Math.sqrt(tiles.size()); i++){
            for(int j=0; j < Math.sqrt(tiles.size()); j++){
                output = output + tiles.get(j*i).toString();
            }
            output = output + "\n";
        }
        return output;
=======
        return tiles = tiles.stream().sorted().collect(Collectors.toList());
>>>>>>> 60c2cee9d3d16546f8a1fd5981791c7d92d4bca1
    }
}
