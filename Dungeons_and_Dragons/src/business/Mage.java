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
    }

    @Override
    public String describe() {
        return super.describe() + String.format("\t\tMana: %d\t\tMana pool: %d\t\tSpell Power: %d\t\tHits Count: %d\t\tAbility Range: %d",
                currentMana, manaPool, spellPower, hitsCount, abilityRange);
    }

    public void castAbility(ArrayList<Enemy> enemies, Player p) {
        if(currentMana < manaPool){
            messageCallback.print("not enough mana to activate the broken skill");
        }
        else {
            int hit = 0;
            while (hit < getHitsCount() & EnemyInRange(enemies)) {
                int x = (int) Math.floor(Math.random() * enemies.size());
                Enemy enemy = enemies.get(x);
                int defense = (int) Math.floor(enemy.getDefensePoints() * Math.random());
                enemy.takeDamage(getSpellPower() - defense);
                hit++;
            }
            setCurrentMana(getCurrentMana() - getManaCost());
        }
    }

    private boolean EnemyInRange(ArrayList<Enemy> enemies){
        for(Enemy enemy:enemies){
            if(this.range(enemy) <= this.getSpellRange()){
                return true;
            }
        }
        return false;
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
