import java.util.ArrayList;

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

  public String attack(ArrayList<Adventurer> other, int n) {
    return "Swordsman attacked " + other.get(n).getName();
  }

  public String specialAttack(ArrayList<Adventurer> other, int n) {
    return "Swordsman special attacked " + other.get(n).getName();
  }

  public String support() {
    // increase attack damage next time
    return "Swordsman sharpened his blade! Increases next attack damage by 1.5x"; 
  }

  public String support(ArrayList<Adventurer> other, int n) {
    // increase attack damage of a teammate
    return "Swordsman somehow increased the damage of someone else! Increases " + other.get(n).getName() + "'s next attack damage by 1.5x."; 
  }
}
