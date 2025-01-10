public class Swordsman extends Adventurer {
  int HP;
  int Rage, maxRage;

  public Swordsman() {
    HP = 30;
    Rage = 0;
    maxRage = 10;
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

  public String attack(Adventurer other) {
    return "Swordsman attacked " + other.getName();
  }

  public String specialAttack(Adventurer other) {
    return "Swordsman special attacked " + other.getName();
  }

  public String support() {
    // increase attack damage next time
    return "Swordsman sharpened his blade! Increases next attack damage by 1.5x"; 
  }

  public String support(Adventurer other) {
    // increase attack damage of a teammate
    return "Swordsman somehow increased the damage of someone else! Increases " + other.getName() + "'s next attack damage by 1.5x."; 
  }
}
