package business;

import business.Enemy;

public class Trap extends Enemy {
    Integer visibilityTime;
    Integer invisibilityTime;
    Integer ticksCount;
    Boolean visible;

    public Trap(char tile, String name, int healthPool, int attack, int defense, int exp, Integer vt, Integer it) {
        super(tile, name, healthPool, attack, defense, exp);
        visibilityTime = vt;
        invisibilityTime = it;
        ticksCount = 0;
        visible = true;
    }
    public void move(Player player){
        visible = ticksCount < visibilityTime;
        if( ticksCount == (visibilityTime + invisibilityTime )){
            ticksCount = 0;
        }
        else {
            ticksCount = ticksCount + 1;
        }
        if(this.range(player) < 2){
            battle(player);
        }
    }
    @Override
    public String toString(){
        if(visible){
            return String.valueOf(tile);
        }
        else{
            return ".";
        }
    }
}
