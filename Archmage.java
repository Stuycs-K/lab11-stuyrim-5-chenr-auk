import java.util.ArrayList;

public class Archmage extends Adventurer{
    int mana, maxMana;
    public Archmage(String name, int hp){
        super(name,hp);
        maxMana = 20;
        mana = maxMana/2;
      }
    
    public Archmage(String name){
        this(name,20);
      }
    
    public Archmage(){
        this("Arch Mage");
      }

    public String getSpecialName() {
        return "mana";
    }
 
    public int getSpecial() {
        return mana;
    }

    public int getSpecialMax() {
        return maxMana;
    }

    public void setSpecial(int n) {
        mana = n;
    }

    public String attack(ArrayList<Adventurer> others, int n) {
        Adventurer enemy = others.get(n);
        int damage = mana / 2;
        enemy.applyDamage(damage);
        restoreSpecial(2);
        return this + " hit " +enemy+ " for " + damage + " damage and channeled 2 mana!";
    }

    public String support(ArrayList<Adventurer> others, int n) {
        
    }
}
