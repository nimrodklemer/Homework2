package business;

import java.util.ArrayList;

public class Mage extends Player {

    Integer manaPool;
    Integer currentMana;
    Integer spellPower;
    Integer manaCost;
    Integer hitsCount;
    Integer abilityRange;

    public Mage(String Name, int HealthPool, int Attack,int Defense, int ManaPool, int ManaCost, int SpellPower, int HitsCount, int AbilityRange) {
        super(Name, HealthPool, Attack, Defense);
        manaPool = ManaPool;
        currentMana = ManaPool / 4;
        spellPower = SpellPower;
        hitsCount = HitsCount;
        abilityRange = AbilityRange;
        manaCost = ManaCost;
    }


    @Override
    public void levelUp() {
        super.levelUp();
        manaPool+=25*playerLevel;
        int a = currentMana+manaPool/4, b = manaPool;
        currentMana = Math.min(a, b);
        spellPower = spellPower + 10 * playerLevel;
    }

    @Override
    public void processStep() {
        currentMana = Math.min(manaPool, currentMana + getPlayerLevel());
    }

    @Override
    public String describe() {
        return super.describe() + String.format("\t\tMana: %d\t\tMana pool: %d\t\tSpell Power: %d\t\tHits Count: %d\t\tAbility Range: %d",
                currentMana, manaPool, spellPower, hitsCount, abilityRange);
    }

    public void castAbility(ArrayList<Enemy> enemies, Player p) {
        if(currentMana < manaCost){
            messageCallback.print("not enough mana to activate the broken skill");
        }
        else {
            int hit = 0;
            int power = getSpellPower();
            if(EnemyInRange(enemies)) {
                while (hit < getHitsCount() & EnemyInRange(enemies)) {
                    ArrayList<Enemy> ListEnemy = ListEnemiesInRange(enemies);
                    int x = (int) Math.floor(Math.random() * ListEnemy.size());
                    Enemy enemy = ListEnemy.get(x);
                    int defense = (int) Math.floor(enemy.getDefensePoints() * Math.random());
                    messageCallback.print("you dealt to " + enemy.getName() + " " + Math.max(0, power - defense) + " with your special ability");
                    enemy.takeDamage(power - defense);
                    if(enemy.getHealth() == 0){
                        enemy.death();
                        this.addXP(enemy.getExperienceValue());
                    }
                    hit++;

                }
                setCurrentMana(getCurrentMana() - getManaCost());
            }
            else{
                messageCallback.print("no enemy within range");
            }
        }
    }

    private boolean EnemyInRange(ArrayList<Enemy> enemies){
        for(Enemy enemy:enemies){
            if(this.range(enemy) < this.getSpellRange() & enemy.isAlive()){
                return true;
            }
        }
        return false;
    }

    private ArrayList<Enemy> ListEnemiesInRange(ArrayList<Enemy> enemies){
        ArrayList<Enemy> ListEnemy = new ArrayList<>();
        for(Enemy enemy:enemies){
            if(this.range(enemy) < this.getSpellRange() & enemy.isAlive()){
                ListEnemy.add(enemy);
            }
        }
        return ListEnemy;
    }

    public int getSpellRange(){
        return abilityRange;
    }
    public int getSpellPower(){
        return spellPower;
    }

    public Integer getHitsCount() {
        return hitsCount;
    }

    public Integer getManaCost() {
        return manaCost;
    }

    public Integer getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(Integer currentMana) {
        this.currentMana = currentMana;
    }

}
