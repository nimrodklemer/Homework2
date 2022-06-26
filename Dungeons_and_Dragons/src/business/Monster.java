package business;

public class Monster extends Enemy {
    Integer visionRange;

    public Monster(char Tile, String Name, int Health, int Attack, int Defence, int ExperienceValue, int VisionRange) {
        super(Tile, Name, Health, Attack, Defence, ExperienceValue);
        visionRange = VisionRange;
    }

    public void move(Player p) {
        int dx, dy;
        if(this.range(p) < visionRange){
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
        }

        int direction = (int) (Math.random()*3);
        switch (direction) {
            case 0 -> this.moveLeft();
            case 1 -> this.moveRight();
            case 2 -> this.moveUp();
            case 3 -> this.moveDown();
        }

    }
}
