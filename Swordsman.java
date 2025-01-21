import java.util.ArrayList;

public class Swordsman extends Adventurer {
  int HP;
  int Rage, maxRage;

  public Swordsman(String name, int HP) {
    super(name, HP);
    HP = 30;
    Rage = 0;
    maxRage = 5;
  }

  public Swordsman(String name) {
    this(name, 30);
  }

  public Swordsman() {
    this("Swordsman");
  }

  public String getSpecialName() {
    return "Rage";
  }

  public int getSpecial() {
    return Rage;
  }

  public int getSpecialMax() {
    return maxRage;
  }

  public void setSpecial(int n) {
    this.Rage = n;
  }

  public String attack(ArrayList<Adventurer> other, int n) {
    int damage = (int)(((Math.random()*5)+5)*this.damageMult());
    int bleed = (int)(Math.random()*5);

    if (bleed == 0) {
      other.get(n).applyDamage(damage);
      other.get(n).applyBleed();
      restoreSpecial(3);

      return this + " attacked " + other.get(n).getName() + " for " + damage + " damage and applied BLEED for two turns. " + this + " gained 2 Rage.";
    }

    else {
      other.get(n).applyDamage(damage);
      restoreSpecial(2);

      return this + " attacked " + other.get(n).getName() + " for " + damage + " damage. " + this + " gained 1 Rage.";
    }
  }

  public String specialAttack(ArrayList<Adventurer> other, int n) {
    if (this.getSpecial() >= 3) {
      int otherHealth = other.get(n).getHP();
      if (otherHealth < other.get(n).getmaxHP() / 2) {
        other.get(n).setHP(0);
        this.setSpecial(0);
        return this + " dealt a fatal blow to " + other.get(n).getName() + "!";
      }
      else {
        int damage = (int)(10*this.damageMult());
        other.get(n).applyDamage(damage);
        this.setSpecial(this.getSpecial()-3);
        return this + " attacked " + other.get(n).getName() + " for " + damage + " damage.";
      }
    }
    else {
      return "Not enough Rage! " + attack(other, n);
    }
  }

  public String support() {
    this.setDamageMult(1.5);
    return "Swordsman increased its own damage. Increases next attack damage by 1.5x."; 
  }

  public String support(ArrayList<Adventurer> other, int n) {
    other.get(n).setDamageMult(1.5);
    return "Swordsman increased the damage of " + other.get(n).getName() + ". Increases " + other.get(n).getName() + "'s next attack damage by 1.5x."; 
  }
}
