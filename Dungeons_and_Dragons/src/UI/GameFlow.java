package UI;

import business.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameFlow {

    private static GameBoard board;
    private static int currentBoard = 0;
    private static final TileFactory factory = new TileFactory();
    private static Player player;
    private static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private static Actions actions;
    private static MessageCallback messageCallback;
    private static int character;


    private static void tick(){
        for (Enemy enemy : enemies) {
            if (!enemy.isAlive()) {
                enemies.remove(enemy);
            } else {
                enemy.move(player);

            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome young traveler, please select a player character(write the number of the wanted character");
        System.out.println("1 : Jon Snow");
        System.out.println("2 : The Hound");
        System.out.println("3 : Melisandre");
        System.out.println("4 : Thoros of Myr");
        System.out.println("5 : Arya Stark");
        System.out.println("6 :  Bronn");
        System.out.println("7 :  Ygritte");
        decideCharacter(scan); // pick the character
        System.out.println("You have 6 actions to use:");
        System.out.println("Move left. (for that write a)");
        System.out.println("Move right. (for that write d)");
        System.out.println("Move up. (for that write w)");
        System.out.println("Move down. (for that write s)");
        System.out.println("cast special ability. (for that write e)");
        System.out.println("do noting. (for that write q)");
        System.out.println("to continue press any key");
        scan.next();//to pause

        File levelPath = new File(args[0]);
        File levels = new File(String.valueOf(levelPath.getCanonicalFile()));
        File[] levelsList = levels.listFiles();
        assert levelsList != null;
        createBoard(levelsList[currentBoard].getPath());
        System.out.println(board.toString());
        //the actual game
        while(player.getIsAlive()){//continue to the next level if the player is alive(means that he win the round
            actions = new Actions(enemies, player);
            boolean winLevel = false;

            while(!winLevel() && player.getIsAlive()){//in level, when exit loop move level

                while(true) {
                    String action = scan.next();
                    if (action.length() == 1 && actions.isValidAction(action.charAt(0))) {
                        actions.doAction(action.charAt(0));
                        tick();
                        System.out.println(player.describe());
                        System.out.println(board.toString());
                        break;
                    } else {
                        System.out.println("that's not a valid action");
                    }
                }
            }
            currentBoard++;//go to the next level
            createBoard(levelsList[currentBoard].getPath());
        }

    }
    public static void createBoard(String path){


        int j=0;
        int Y =sizeY(path);
        int X = sizeX(path);
        Tile[][] Board = new Tile[Y][X];
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                for(int i=0; i<line.length(); i++){
                    if(line.charAt(i) == '@'){
                        player = factory.producePlayer(character, new Position(j,i), messageCallback);
                        player.setMessageCallback((message) -> System.out.println(message));
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
                        enemy.setMessageCallback((message) -> System.out.println(message));
                        enemy.setDeathCall(() -> GameBoard.remove(enemy));
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

    private static boolean winLevel(){
        if(enemies.isEmpty()){
            System.out.println("good job you finished the " + (currentBoard +1) +"prepare yourself to the next one");
            return true;
        }
        return false;
    }

    private static void decideCharacter(Scanner scan){
        String input;
        while(true) {
            input = scan.next();//gets character
            if (input.length() == 1 & input.charAt(0) >= '1' & input.charAt(0) <= '7') {
                character = input.charAt(0) - '0';
                break;
            } else {
                System.out.println("Invalid input");
            }
        }
    }
}
