import java.util.ArrayList;

public class Archmage extends Adventurer{
    int mana, maxMana;
    public Archmage(String name, int hp){
        super(name,hp);
        maxMana = 40;
        mana = maxMana;
      }
    
    public Archmage(String name){
        this(name,20);
      }
    
    public Archmage(){
        this("Archmage");
      }

    public String getSpecialName() {
        return "Mana";
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
        double damage = (mana / 2)*this.damageMult();
        enemy.applyDamage((int)damage);
        restoreSpecial(2);
        return this + " hit " + enemy + " for " + damage + " damage and channeled 2 mana!";
    }

    public String support(ArrayList<Adventurer> others, int n) {
        Adventurer ally = others.get(n);
        int shield = mana;
        ally.setHP(shield + ally.getHP());
        mana = mana / 2;
        return this + " shielded " + ally + " for " + shield + " shield and used " + mana + " mana!";
    }

    public String support() {
        mana += 5; 
        return this + " channeled 5 mana!";
    }

    public String specialAttack(ArrayList<Adventurer> others, int n) {
        for (int i = 0; i < others.size(); i++) {
            others.get(i).applyFreeze();
        }
        mana = 0;
        return this + " froze all enemies for one turn!";
    }
}
