public class Monster extends Enemy{
    Integer visionRange;

    public Monster(char Tile, String Name, int Health, int Attack, int Defence, int ExperienceValue, int VisionRange) {
        super(Tile, Name, Health, Attack, Defence, ExperienceValue);
        visionRange = VisionRange;
    }
}
