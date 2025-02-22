import java.util.*;

public class Game{
  private static final int WIDTH = 80;
  private static final int HEIGHT = 30;
  private static final int BORDER_COLOR = Text.BLUE;
  private static final int BORDER_BACKGROUND = Text.WHITE + Text.BACKGROUND;

  public static void main(String[] args) {
    run();
  }

  //Display the borders of your screen that will not change.
  //Do not write over the blank areas where text will appear or parties will appear.
  public static void drawBackground(){
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //YOUR CODE HERE

    // horizontal borders
    System.out.print ("\033[;36m");
    for (int i = 1; i < 81; i++) {
      drawText("=", 1, i);  // top
      drawText("=", 31, i); // bottom

      drawText("=", 6,i); // border for enemy list and prints
      drawText("=", 21,i); // border between ally list and prints
      drawText("=", 26,i); // border between ally list and user input

    }
    // vertical border
    for (int j = 1; j < 32; j++) {
      drawText("|", j, 1);
      drawText("|", j, 81);

      // borders separating allies
      if (j < 26 && j > 21 || j < 6 && j > 1) {
        drawText("|", j, 21);
        drawText("|", j, 41);
        drawText("|", j, 61);
      }
    }
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
  }

  //Display a line of text starting at
  //(columns and rows start at 1 (not zero) in the terminal)
  //use this method in your other text drawing methods to make things simpler.
  public static void drawText(String s,int startRow, int startCol){
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    Text.go(startRow, startCol);
    System.out.print(s);
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
  }

  /*Use this method to place text on the screen at a particular location.
  *When the length of the text exceeds width, continue on the next line
  *for up to height lines.
  *All remaining locations in the text box should be written with spaces to
  *clear previously written text.
  *@param row the row to start the top left corner of the text box.
  *@param col the column to start the top left corner of the text box.
  *@param width the number of characters per row
  *@param height the number of rows
  */
  public static void TextBox(int row, int col, int width, int height, String text){
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    String partial = " ";
    int theRow = row; // theRow = the current row the cursor is at

    // clear the area first
    for (int i=0; i<height; i++) {
      for (int j=0; j<width; j++) {
        drawText(partial, theRow+i, col+j);
      }
    }
    Text.go(row, col);

    // create an arraylist toPrint that is the words that are to be displayed
    ArrayList<String>toPrint = new ArrayList<String>();
    String[] temp = text.split(" ");
    for (String elem : temp) {
      toPrint.add(elem);
    }

    //int counter = 1;

    // loop that displays the words
    while (toPrint.size() > 0 && theRow <= row + height-1) { // while there are words to be displayed
      // System.out.println("count number " + counter);
      // counter++;
      partial = "";
      while (partial.length() < width && toPrint.size()>0) { 
        // add words to partial until it gets longer than the width
        // and remove the words from toPrint
        if (partial.length() + toPrint.get(0).length() <= width) { 
          partial += toPrint.get(0) + " ";
          toPrint.remove(0);
        }
        // there is no case for when the word is longer than the width, 
        // but that won't happen when we make this game anyways
        else {
          break;
        }
      }
      // print the line
      drawText(partial, theRow, col);
      theRow++;
    }

    Text.go(theRow+1, 0);
  }
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/


    //return a random adventurer (choose between all available subclasses)
    //feel free to overload this method to allow specific names/stats.
    public static Adventurer createRandomAdventurer(){
      int chooser = (int)(Math.random()*3);
      if (chooser == 0) {
        return new Archmage();
      }

      else if (chooser == 1) {
        return new Swordsman();
      }

      else {
        return new CodeWarrior("Bob");
      }
    }

    /*Display a List of 2-4 adventurers on the rows row through row+3 (4 rows max)
    *Should include Name HP and Special on 3 separate lines.
    *Note there is one blank row reserved for your use if you choose.
    *Format:
    *Bob          Amy        Jun
    *HP: 10       HP: 15     HP:19
    *Caffeine: 20 Mana: 10   Snark: 1
    * ***THIS ROW INTENTIONALLY LEFT BLANK***
    */
    public static void drawParty(ArrayList<Adventurer> party,int startRow){
      /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
      //YOUR CODE HERE
      int col = 2;
      int counter = 0; 
      while (counter < party.size()) {
        drawText(party.get(counter).getName(), startRow, col);
        if (party.get(counter).getHP() > 0) {
          TextBox(startRow+1, col, 18, 1, "HP:"+colorByPercent(party.get(counter).getHP(), party.get(counter).getmaxHP()));
          TextBox(startRow+2, col, 18, 1, party.get(counter).getSpecialName() + ": " + party.get(counter).getSpecial() + "/" + party.get(counter).getSpecialMax());
          TextBox(startRow+3, col, 18, 1, "Status: " + party.get(counter).printStatus());
        }
        else {
          drawText("HP: DEAD   ", startRow+1, col);
          TextBox(startRow+2, col, 18, 1, "");
          TextBox(startRow+3, col, 18, 1, "");
        }

        col += 20;
        counter++;
      }
      /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
    }


  //Use this to create a colorized number string based on the % compared to the max value.
  public static String colorByPercent(int hp, int maxHP){
    String output = String.format("%2s", hp+"")+"/"+String.format("%2s", maxHP+"");
    //COLORIZE THE OUTPUT IF HIGH/LOW:
    // under 25% : red
    if (hp <= (double)(0.25*maxHP)) {
      return Text.colorize(output, Text.RED);
    }
    // under 75% : yellow
    else if (hp <= (double)(0.75*maxHP)) {
      return Text.colorize(output, Text.YELLOW);
    }
    // otherwise : white
    else {
      return output;
    }
  }





  //Display the party and enemies
  //Do not write over the blank areas where text will appear.
  //Place the cursor at the place where the user will by typing their input at the end of this method.
  public static void drawScreen(ArrayList<Adventurer> allies, ArrayList<Adventurer> enemies){

    drawBackground();

    //draw player party
    drawParty(allies, 22);

    //draw enemy party
    drawParty(enemies, 2);
  }

  public static String userInput(Scanner in){
      //Move cursor to prompt location
      Text.go(28, 2);

      //show cursor
      Text.showCursor();

      String input = in.nextLine();

      //clear the text that was written

      return input;
  }

  public static void quit(){
    Text.reset();
    Text.showCursor();
    Text.go(32,1);
  }

  public static void displayMoveset(ArrayList<Adventurer>party, int whichPlayer) {
    if (party.get(whichPlayer).getName() == "Swordsman") {
      TextBox(15, 2, 24, 6, "ATTACK: SWORD SLASH deals 5-10 damage and has a 20% chance to apply BLEED for 2 turns; gains 1 Rage");
      TextBox(15, 28, 24, 6, "SPECIAL ATTACK: LETHAL STRIKE [Requires 3 Rage] kills opponent if they have 1/2 HP or less; if not, deals 10 damage");
      TextBox(15, 54, 24, 6, "SUPPORT: SHARPEN increases the damage of a team member by 1.5x for the next attack; gains 1 Rage");
    }
    else if (party.get(whichPlayer).getName() == "Archmage") {
      TextBox(15, 2, 24, 6, "ATTACK: BLAST deals damage equal to half this Archmage's mana; gains 2 Mana");
      TextBox(15, 28, 24, 6, "SPECIAL ATTACK: FREEZE STORM [Requires 40 Mana] freezes all opponents for one turn");
      TextBox(15, 54, 24, 6, "SUPPORT: FORCE FIELD projects a shield that blocks 20 damage onto a team member; gains 2 Mana");
    }
    else if (party.get(whichPlayer).getName() == "Bob") {
      TextBox(15, 2, 24, 6, "ATTACK deals 2-7 damage, restores 2 caffeine");
      TextBox(15, 28, 24, 6, "SPECIAL ATTACK [Requires 8 caffeine] deals 3-12 damage");
      TextBox(15, 54, 24, 6, "SUPPORT increases a team member's special resource");
    }
  }

  public static boolean allDead(ArrayList<Adventurer> team) {
    // checks if one team is all dead
    boolean end = true;
    for (int i = 0; i < team.size(); i++) {
      if (team.get(i).getHP() > 0) {
        end = false;
      }
    }
    return end;
  }


  public static void win() {
    Text.clear();
    System.out.println("YOU WIN! Thanks for playing!");
    System.exit(0);
  }

  public static void lose() {
    Text.clear();
    System.out.println("YOU LOST! Better luck next time!");
    System.exit(0);
  }

  public static void run(){
    //Clear and initialize
    Text.hideCursor();
    Text.clear();
    System.out.print ("\033[;37m");

    //Things to attack:
    //Make an ArrayList of Adventurers and add 1-3 enemies to it.
    //If only 1 enemy is added it should be the boss class.
    //start with 1 boss and modify the code to allow 2-3 adventurers later.
    ArrayList<Adventurer>enemies = new ArrayList<Adventurer>();
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    int enemyCount = 1 + (int) (Math.random() * 3);
    if (enemyCount == 1) {
      enemies.add(new Boss());
    }
    else {
      for (int i = 0; i < enemyCount; i++) {
        enemies.add(createRandomAdventurer());
      }
    }
    //YOUR CODE HERE
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

    //Adventurers you control:
    //Make an ArrayList of Adventurers and add 2-4 Adventurers to it.
    ArrayList<Adventurer> party = new ArrayList<>();
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //YOUR CODE HERE
    int partyCount = 2 + (int) (Math.random() * 3);
    for (int i = 0; i < partyCount; i++) {
      party.add(createRandomAdventurer());
    }
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

    // ============================================================== start of the game ============================================================= 
    boolean partyTurn = true;
    int whichPlayer = 0;
    int whichOpponent = 0;
    int whichTeammate = 0;
    int turn = 0;
    String input = "";//blank to get into the main loop.
    Scanner in = new Scanner(System.in);
    //Draw the window border

    //You can add parameters to draw screen!
    drawScreen(party, enemies);//initial state.

    //Main loop

    //display this prompt at the start of the game.
    String preprompt = "Enter command for "+party.get(whichPlayer)+": attack/support/special/quit 1-" + enemies.size();
    TextBox(27, 2, 77, 1, preprompt);
    if (whichPlayer < party.size()) {
      displayMoveset(party, whichPlayer);
    }  

    // ============================================================== start of game loop ============================================================= 
    while((! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")))){
      //Read user input
      input = userInput(in);

      //example debug statment
      // TextBox(24,2,1,78,"input: "+input+" partyTurn:"+partyTurn+ " whichPlayer="+whichPlayer+ " whichOpp="+whichOpponent);

      //display event based on last turn's input
      ////////
      if(partyTurn){
        drawText("\r", 7, 2);

        if (party.get(whichPlayer).getHP() <= 0) {
          TextBox(10, 2, 40, 5, party.get(whichPlayer) + " is dead and cannot move!");
          whichPlayer++;
          continue;
        }

        //Process user input for the last Adventurer:
        if((input.startsWith("attack ") || input.startsWith("a ")) && (input.endsWith("1") || input.endsWith("2") || input.endsWith("3"))){
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          drawText("                          ", 28, 2); // to block out previous input
          drawText("                          ", 29, 2); // to block out invalid input text
          whichOpponent = Integer.parseInt(input.substring(input.length()-1)) - 1;
          if (enemies.get(whichOpponent).getHP() <= 0) {
            try {
              TextBox(10, 2, 40, 5,party.get(whichPlayer).attack(enemies, whichOpponent-1));
            }
            catch (Exception e) {
              TextBox(10, 2, 40, 5,party.get(whichPlayer).attack(enemies, whichOpponent + 1));
            }
          }
          else {
            TextBox(10, 2, 40, 5,party.get(whichPlayer).attack(enemies, whichOpponent));
          }
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        else if((input.startsWith("special ") || input.startsWith("sp ")) && (input.endsWith("1") || input.endsWith("2") || input.endsWith("3"))){
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          drawText("                          ", 28, 2); // to block out previous input
          drawText("                          ", 29, 2); // to block out invalid input text
          if (enemies.get(whichOpponent).getHP() <= 0) {
            try {
              TextBox(10, 2, 40, 5,party.get(whichPlayer).specialAttack(enemies, whichOpponent-1));
            }
            catch (Exception e) {
              TextBox(10, 2, 40, 5,party.get(whichPlayer).specialAttack(enemies, whichOpponent + 1));
            }
          }
          else {
            TextBox(10, 2, 40, 5,party.get(whichPlayer).specialAttack(enemies, whichOpponent));
          }
          //YOUR CODE HERE
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        else if((input.startsWith("su ") || input.startsWith("support ")) && (input.endsWith("1") || input.endsWith("2") || input.endsWith("3"))){
          //"support 0" or "su 0" or "su 2" etc.
          //assume the value that follows su  is an integer.
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          drawText("                          ", 28, 2); // to block out previous input
          drawText("                          ", 29, 2); // to block out invalid input text

          whichTeammate = Integer.parseInt(input.substring(input.length()-1));
          if (! (party.get(whichTeammate - 1).getStatus().equals("none"))) {
            TextBox(10, 2, 40, 5,party.get(whichPlayer).support(party, whichTeammate-1));
          }
          else {
            TextBox(10, 2, 40, 5,party.get(whichTeammate - 1) + "already has a status effect!");
          }
        
          //YOUR CODE HERE
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        else if (input.startsWith("q") || input.startsWith("quit")) {
          quit();
        }
        else {
          TextBox(28, 2, 77, 1, " "); // to block out previous input
          TextBox(29, 2, 77, 1, "Invalid input, try again!");
          whichPlayer--;
        }

        //You should decide when you want to re-ask for user input
        //If no errors:
        whichPlayer++;


        if(whichPlayer < party.size()){
          //This is a player turn.
          //Decide where to draw the following prompt:
          String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/support/quit 1-" + enemies.size();
          TextBox(27, 2, 77, 1, prompt);
        }

        else{
          //This is after the player's turn, and allows the user to see the enemy turn
          //Decide where to draw the following prompt:
          String prompt = "Press enter to see monster's turn                                                      ";
          drawText(prompt, 27, 2);

          partyTurn = false;
          whichOpponent = 0;
        }

        if (allDead(enemies)) {
          win();
        }
        //done with one party member
      }/////////////////-------------

      else{
        drawText("\r", 7, 2);
          //not the party turn!
          //enemy attacks a randomly chosen person with a randomly chosen attack.`
          //Enemy action choices go here!
          if (enemies.get(whichOpponent).getHP() <= 0) {
            TextBox(10, 2, 40, 5, enemies.get(whichOpponent) + " is dead and cannot move!");
            whichOpponent++;
            continue;
          }
          if (whichOpponent < enemies.size()) {
            int randomPlayer = (int) (Math.random() * enemies.size());
            int randomAction = (int) (Math.random() * 3);

              if (randomAction == 0) {
                if (party.get(randomPlayer).getHP() <= 0) {
                  try {
                  TextBox(10, 2, 40, 5,enemies.get(whichOpponent).attack(party, randomPlayer + 1));
                  TextBox(10, 2, 40, 5,enemies.get(whichOpponent).attack(party, randomPlayer + 2));
                  }
                  catch (Exception e) {
                    TextBox(10, 2, 40, 5,enemies.get(whichOpponent).attack(party, randomPlayer - 1));
                  }
                }
                else {
                  TextBox(10, 2, 40, 5,enemies.get(whichOpponent).attack(party, randomPlayer));
                }
            } 
              if (randomAction == 1) {
                if (party.get(randomPlayer).getHP() <= 0) {
                try {
                TextBox(10, 2, 40, 5,enemies.get(whichOpponent).specialAttack(party, randomPlayer + 1));
                }
                catch (Exception e) {
                  TextBox(10, 2, 40, 5,enemies.get(whichOpponent).specialAttack(party, randomPlayer - 1));
                }
              }
              else {
              TextBox(10, 2, 40, 5,enemies.get(whichOpponent).specialAttack(party, randomPlayer));
                }
            }
              if (randomAction == 2) {
                TextBox(10, 2, 40, 5,enemies.get(whichOpponent).support(enemies, randomPlayer));
              }
            }
            //Decide where to draw the following prompt:
            String prompt = "press enter to see next turn";

          whichOpponent++;

        for (int i = 0; i < party.size(); i++) {
          if (party.get(i).getHP() <= 0) {
            drawText(party.get(i) + " has tragically died!", 7, 2);
          }
        }
        if (allDead(party)) {
          lose();
        }
      }//end of one enemy.

      //modify this if statement.
      if(!partyTurn && whichOpponent >= enemies.size()){
        //THIS BLOCK IS TO END THE ENEMY TURN
        //It only triggers after the last enemy goes.
        whichPlayer = 0;
        turn++;
        partyTurn=true;
        //display this prompt before player's turn
        String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/support/quit 1-" + enemies.size();
        TextBox(27, 2, 77, 1, prompt);
      }
      //display the updated screen after input has been processed.
      drawScreen(party, enemies);
      // winLose(enemies, party);


    }//end of main game loop
    // ============================================================== start of game loop ============================================================= 


    //After quit reset things
    quit();
  }
}