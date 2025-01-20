import java.util.Random;

import java.util.ArrayList;

public abstract class Adventurer{
  private String name;
  private int HP,maxHP;
  private ArrayList<String>statusEffect;
  private ArrayList<Integer>statusTurns;

  //Abstract methods are meant to be implemented in child classes.
  /*
  all adventurers must have a custom special
  consumable resource (mana/rage/money/witts etc)
  */

  //give it a short name (fewer than 13 characters)
  public abstract String getSpecialName();
  //accessor methods
  public abstract int getSpecial();
  public abstract int getSpecialMax();
  public abstract void setSpecial(int n);

  //concrete method written using abstract methods.
  //refill special resource by amount, but only up to at most getSpecialMax()
  public int restoreSpecial(int n){
    if( n > getSpecialMax() - getSpecial()){
      n = getSpecialMax() - getSpecial();
    }
    setSpecial(getSpecial()+n);
    return n;
  }

  /*
  all adventurers must have a way to attack enemies and
  support their allys
  */
  //hurt or hinder the target adventurer
  public abstract String attack(ArrayList<Adventurer> others, int n);

  /*This is an example of an improvement that you can make to allow
   * for more flexible targetting.
   */
  //heal or buff the party
  //public abstract String support(ArrayList<Adventurer> others);

  //heal or buff the target adventurer
  public abstract String support(ArrayList<Adventurer> others, int n);

  //heal or buff self
  public abstract String support();

  //hurt or hinder the target adventurer, consume some special resource
  public abstract String specialAttack(ArrayList<Adventurer> others, int n);

  /*
  standard methods
  */

  public void applyDamage(int amount){
    this.HP -= amount;
  }

  //You did it wrong if this happens.
  public Adventurer(){
    this("Lester-the-noArg-constructor-string");
  }

  public Adventurer(String name){
    this(name, 10);
  }

  public Adventurer(String name, int hp){
    this.name = name;
    this.HP = hp;
    this.maxHP = hp;
  }

  //toString method
  public String toString(){
    return this.getName();
  }

  //Get Methods
  public String getName(){
    return name;
  }

  public int getHP(){
    return HP;
  }

  public int getmaxHP(){
    return maxHP;
  }
  public void setmaxHP(int newMax){
    maxHP = newMax;
  }

  //Set Methods
  public void setHP(int health){
    this.HP = health;
  }

  public void setName(String s){
    this.name = s;
  }

  public void setStatus(String s, int turnCount) {
    statusEffect.add(s);
    statusTurns.add(turnCount);
  }

  public String printStatus() {
    String result = "";
    for (int i=0; i<statusEffect.size(); i++) {
      if (i==statusEffect.size()-1) {
        result+=statusEffect.get(i) + ;
      }
      else {
        result+=statusEffect.get(i)+", ";
      }
    }
    return result;
  }

  public ArrayList<String> getStatus() {
    return statusEffect;
  }

  public void applyBleed() {
    this.setStatus("bleed", 2);
  }

  public String bleed() {
    
  }

  public void applyFreeze() {
    this.setStatus("freeze", 1);
  }

  public String freeze() {
    
  }

  public int damageMult() {

  }
}
