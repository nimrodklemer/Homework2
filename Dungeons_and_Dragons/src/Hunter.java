public class Hunter extends Player{

    Integer range;
    Integer arrowsCount;
    Integer ticksCount;

    public Hunter(String Name, int HealthPool,int Attack, int Defense, int Range) {
        super(Name, HealthPool, Attack, Defense);
        this.range = Range;
        this.arrowsCount = 10*playerLevel;
        ticksCount = 0;
    }


    @Override
    public void castAbility() {
        return;
    }

}
