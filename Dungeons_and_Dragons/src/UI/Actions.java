package UI;
import business.*;

public class Actions {
    public void moveUp(){

    }

    public void moveDown(){

    }

    public void moveLeft(){

    }

    public void moveRight(){

    }

    public void castSpecialAbility(Enemy[] enemies){

    }

    public void castSpecialAbility(Player player){

    }

    public void doNothing(){

    }
    public void enemyDoAction(Player player){
        act(player);
    }

    public void doAction(char action, Enemy[] enemies){
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
            castSpecialAbility(enemies);
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
