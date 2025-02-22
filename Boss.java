import java.util.ArrayList;

public class Boss extends Adventurer{
    int attackPower, maxAD;
    public Boss(String name, int hp){
        super(name,hp);
        attackPower = 10;
        maxAD = 50;
      }
    
    public Boss(String name){
        this(name,100);
      }
    
    public Boss(){
        this("Ultimate Boss");
      }
 
    public int getSpecial() {
        return attackPower;
    }

    public String getSpecialName() {
        return "AttackPower";
    }
    public int getSpecialMax() {
        return maxAD;
    }

    public void setSpecial(int n) {
        attackPower = n;
    }

    public String attack(ArrayList<Adventurer> others, int n) {
        Adventurer enemy = others.get(n);
        enemy.applyDamage(10);
        restoreSpecial(2);
        return this + " hit " + enemy + " for 10 damage and gained 2 attack power!";
    }

    public String support(ArrayList<Adventurer> others, int n) {
        return "No allies to support!";
    }

    public String support() {
        this.setHP(this.getHP() + 25);
        return this + " healed itself for 25 health!";
    }

    public String specialAttack(ArrayList<Adventurer> others, int n) {
        int damage = attackPower * 2;
        if (attackPower == 50) {
            for (int i = 0; i < others.size(); i++) {
                others.get(i).applyDamage(damage);
            }
            return this + " attacked all enemies for " + damage + " damage! The game is over!";
        }
        else {
            for (int i = 0; i < others.size(); i++) {
                others.get(i).applyDamage(attackPower);
            }
            return this + " attacked all enemies for " + attackPower + " damage! A devasting blow!";
        }
    }
}
