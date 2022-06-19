package business;
import business.Enemy;

public class Monster extends Enemy {
    Integer visionRange;

    public Monster(char Tile, String Name, int Health, int Attack, int Defence, int ExperienceValue, int VisionRange) {
        super(Tile, Name, Health, Attack, Defence, ExperienceValue);
        visionRange = VisionRange;
    }

    @Override
    public void move(Player p) {
        int dx, dy;
        if(Unit.range(this, p) < VisionRange){
            dx = this.position.x - p.position.x;
            dy = this.position.y - p.position.y;
            if(Math.abs(dx) > Math.abs(dy)){
                if(dx > 0)
                    this.moveLeft();
                else
                    this.moveRight();
            }
            else{
                if(dy > 0)
                    this.moveUp();
                else
                    this.moveDown();
            }
            else{

            }
        }



        int direction = (int) Math.random()*3;
        switch(direction){
            case 0:
                this.moveLeft();
                break;
            case 1:
                this.moveRight();
                break;
            case 2:
                this.moveUp();
                break;
            case 3:
                this.moveDown();
                break;

        }

    }
}
