package UI;

public class Actions {
    public void moveUp(){

    }

    public void moveDown(){

    }

    public void moveLeft(){

    }

    public void moveRight(){

    }

    public void castSpecialAbility(){

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
