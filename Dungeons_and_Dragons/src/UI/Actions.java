package UI;
import business.*;

import java.util.ArrayList;

public class Actions {
    Player player;
    ArrayList<Enemy> enemies;
    public Actions(Player p){
        this.player = p;
    }

    public void moveUp(){
        player.moveUp();
    }

    public void moveDown(){
        player.moveDown();
    }

    public void moveLeft(){
        player.moveLeft();
    }

    public void moveRight(){
        player.moveRight();
    }

    public void castSpecialAbility(){
        player.castAbility();
    }

    public void doNothing(){

    }

    public void doAction(char action){
        if(action == 'w'){
            moveUp();
        }
        if(action == 's'){
            moveDown();
        }
        if(action == 'a'){
            moveLeft();
        }
        if(action == 'd'){
            moveRight();
        }
        if(action == 'q'){
            doNothing();
        }
        if(action == 'e'){
            castSpecialAbility();
        }
    }
    public boolean isValidAction(char action){
        if(action == 'w'){
            return true;
        }
        if(action == 's'){
            return true;
        }
        if(action == 'a'){
            return true;
        }
        if(action == 'd'){
            return true;
        }
        if(action == 'q'){
            return true;
        }
        if(action == 'e'){
            return true;
        }
        return false;
    }
}
