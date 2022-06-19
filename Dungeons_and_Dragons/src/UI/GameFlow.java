package UI;

import business.*;


import java.util.Scanner;

public class GameFlow {

    private static GameBoard board;
    private static int currentBoard = 0;

    private static void tick(){
        for(Enemy enemy:enemies){
            enemy.doSomething();
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
        int character = scan.nextInt();
        Player player = producePlayer(character);
        System.out.println("You have 6 actions to use:");
        System.out.println("Move left. (for that write a)");
        System.out.println("Move right. (for that write d)");
        System.out.println("Move up. (for that write w)");
        System.out.println("Move down. (for that write s)");
        System.out.println("cast special ability. (for that write e)");
        System.out.println("do noting. (for that write q)");


        boolean winLevel = false, death = false;
        Actions actions = new Actions();
        while(!winLevel && !death){
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

    public static Player createPlayer(){
        if(){

        }
    }

}
