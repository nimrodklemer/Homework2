package UI;
import business.*;

import java.util.ArrayList;

public class Actions {
    Player player;
    ArrayList<Enemy> enemies;
    public Actions(ArrayList<Enemy> enemies, Player p){
        this.player = p;
        this.enemies = enemies;
    }

    private void moveUp(){
        player.moveUp();
    }

    private void moveDown(){
        player.moveDown();
    }

    private void moveLeft(){
        player.moveLeft();
    }

    private void moveRight(){
        player.moveRight();
    }

    private void castSpecialAbility(){
        player.castAbility(enemies, null);
    }

    public void doNothing(){
        ;
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
