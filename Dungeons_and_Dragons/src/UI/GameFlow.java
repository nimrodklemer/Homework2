package UI;

import business.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GameFlow {

    private static GameBoard board;
    private static int currentBoard = 0;
    private static final TileFactory factory = new TileFactory();
    private static Player player;
    private static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private static Actions actions;
    private static final MessageCallback messageCallback = new MessageCallback();
    private static int character;

    private static void tick(){
        for(Enemy enemy:enemies){
            enemy.move(player);
        }

    }

    public static void main(String[] args) {
        /*
        boards = new GameBoard[1];
        Tile[][] newBoard = new Tile[3][3];
        //Upper walls.
        newBoard[0][0] = new Wall(0,0);
        newBoard[0][1] = new Wall(0,1);
        newBoard[0][2] = new Wall(0,2);
        //Lower walls.
        newBoard[2][0] = new Wall(2,0);
        newBoard[2][1] = new Wall(2,1);
        newBoard[2][2] = new Wall(2,2);
        //Right-side wall.
        newBoard[1][2] = new Wall(1,2);
        //Empty-space.
        newBoard[1][1] = new EmptySpace(1,1);
        //business.Player.
        newBoard[1][0] = new Warrior(1,0, "Julius Caesar", 1);
        boards[0] = new GameBoard(newBoard);

         */
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome young traveler, please select a player character(write the number of the wanted character");
        System.out.println("1 : Jon Snow");
        System.out.println("2 : The Hound");
        System.out.println("3 : Melisandre");
        System.out.println("4 : Thoros of Myr");
        System.out.println("5 : Arya Stark");
        System.out.println("6 :  Bronn");
        System.out.println("7 :  Ygritte");
        character = scan.nextInt();

        System.out.println("You have 6 actions to use:");
        System.out.println("Move left. (for that write a)");
        System.out.println("Move right. (for that write d)");
        System.out.println("Move up. (for that write w)");
        System.out.println("Move down. (for that write s)");
        System.out.println("cast special ability. (for that write e)");
        System.out.println("do noting. (for that write q)");


        createBoard();
        actions = new Actions(player);




        boolean winLevel = false, death = false;

        while(!winLevel && player.getIsAlive()){
            String action = scan.next();
            if (action.length() == 1 && actions.isValidAction(action.charAt(0))){
                actions.doAction(action.charAt(0));
                tick();
                System.out.println(board.toString());

            }
            else{
                System.out.println("that's not a valid action");
            }
        }
    }
    public static void createBoard(){


        int j=0;
        String path = "C:\\hw-oop-3\\Homework2\\Dungeons_and_Dragons\\gameLevels\\level1.txt";
        int Y =sizeY(path);
        int X = sizeX(path);
        Tile[][] Board = new Tile[Y][X];
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                for(int i=0; i<line.length(); i++){
                    if(line.charAt(i) == '@'){
                        player = factory.producePlayer(character, new Position(j,i), messageCallback, enemies);
                        Board[j][i] = player;
                    }
                    else if(line.charAt(i) == '#'){
                        Board[j][i] = factory.produceWall(new Position(j,i));//position may be wrong
                    }
                    else if(line.charAt(i) == '.'){
                        Board[j][i] = factory.produceEmpty(new Position(j,i));

                    }
                    else{
                        Enemy enemy = factory.produceEnemy(line.charAt(i), new Position(j,i), messageCallback);
                        Board[j][i] = enemy;
                        enemies.add(enemy);
                    }
                }
                j++;

            }
        } catch (FileNotFoundException e) {
            System.out.println ("File not found " + path);
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n" +
                    e.getStackTrace());
        }
        board = new GameBoard(Board, Y, X);
    }

    public static int sizeY(String path) {
        int y = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String next;
            while ((next = reader.readLine()) != null) {
                y++;
            }
        } catch (FileNotFoundException e) {
            System.out.println ("File not found " + path);
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n" +
                    e.getStackTrace());
        }
        return y;
    }

    public static int sizeX(String path) {
        int x = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String next;
            if ((next = reader.readLine()) != null) {
                x = next.length();
            }
        } catch (FileNotFoundException e) {
            System.out.println ("File not found " + path);
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n" +
                    e.getStackTrace());
        }
        return x;
    }
}
