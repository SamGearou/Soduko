
package soduko;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;





/**
 * Main class that solves the Soduko puzzle 
 * @author Sam Gearou
 * @date June 2, 2016
 */
public class Soduko extends JFrame{
    private static int XCoord = 20; //the initial x coordinate for the 81 boxes
    private static int YCoord = 50; //the initial y coordinate for the 81 boxes
    private static final int height = 40; //the height of each box
    private static final int width = 40; //the width of each box
    private static Parse obj  = new Parse(); //Parse object to call parseFile() method
    private static String[][] markupBoard = new String[9][9];
    private static int cellsSolved = 0; //determines when to stop the calculateSodukoBoard method
    private static boolean solvedAnyCells = false; //determines if the evaluate method obtains at least one solution to a cell
    private static ArrayList<Integer> trackedCells = new ArrayList<>(); //tracks all the cells that are filled in from a guess markup cell
    private int index; //an int that is used in calculating the Soduko solution in the evaluate method
    private boolean isAUniqueSolution = true; //determines if a location in a specifix 3x3 box is a unique solution
    private boolean checkedColumns = false; //checks to see that the checkColumns method obtains at least one solution to a cell
    private boolean checkedRows = false;//checsk to see that the checkRows method obttains a least one solution to a cell
    private static char[][] board = new char[9][9]; //the soduko board
    private static boolean[][] openSpot = new boolean[9][9]; //determines which locations of the soduoko board are empty
    
    /**
     * Soduko constructor initializes the JFrame and JPanel
     */
    public Soduko(){
        super("Soduko Solver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        JPanel panel = new JPan();
        panel.setBackground(Color.RED);
        panel.setPreferredSize(new Dimension(600,600));
        setContentPane(panel);
        JSlide slider = new JSlide();
        slider.addChangeListener(new JSlide());
        panel.add(slider, BorderLayout.SOUTH);
        pack();
      }
    
    /**
     * Reads in data from a file and solves the Soduko puzzle
     * @param args- the command line arguments
     * @throws IOException 
     * @throws java.lang.InterruptedException 
     * @throws java.lang.reflect.InvocationTargetException 
     */
 public static void main(String[] args) throws IOException, InterruptedException, InvocationTargetException {
     Soduko sod = new Soduko();
     obj.parseFile();
     for(int i=0; i<9; i++){
         for(int j = 0; j<9; j++){
         board[i][j] = obj.getFileContents()[i].charAt(j);
           getOpenSpot()[i][j] = (board[i][j] == 'b');
         }
     }
     sod.calculuateSodukoBoard(false);
     if(!sod.isDone()){
     sod.markupEmptyCells();
     sod.findPreemptiveSets();
     sod.calculuateSodukoBoard(false);
     sod.updateMarkups();
     }
     if(!sod.isDone()){
         if(!sod.isAValidBoard()){ 
             System.out.println("No Solution");
         }
         else{
         sod.evaluateSmallestMarkup();
     }
     }
 }
 
 /**
  * 
  * @return- XCoord, an int 
  */
 public static int getXCoord(){
     return XCoord;
 }
 
 /**
  * Sets the value of XCoord
  * @param x- x becomes the new coordinate for XCoord 
  */
 public static void setXCoord(int x){
     XCoord=x;
 }
 
 /**
  * Sets the value of YCoord
  * @param y- y becomes the new coordinate for YCoord
  */
 public static void setYCoord(int y){
     YCoord=y;
 }
 
 /**
  * Returns YCoord
  * @return- YCoord, an int 
  */
 public static int getYCoord(){
     return YCoord;
 }
 
 /**
  * getter method for height
  * @return- height, an int 
  */
 public static int getMyHeight(){
     return height;
 }
 
 /**
  * getter method for width
  * @return- width, an int 
  */
 public static int getMyWidth(){
     return width;
 }
 
 

 /**
  *  getter method for 2-D board array
  * @return- board, a 2-D char array 
  */
 public static char[][] getBoard(){
     return board;
 }
 
 /**
  * getter method fro 2-D boolean array openSpot
  * @return- openSpot, a 2-D boolean array 
  */
 public static boolean[][] getOpenSpot(){
     return openSpot;
 }    
    
 /**
  * 
  * @param box- the specific 3x3 box you want to obtain
  * @return- a char array that contains the 9 elements in the specific 3x3 box 
  */
 public char[] getSpecificBox(int box){
     char[] b = new char[9];
     if(box == 1){
         b[0] = board[0][0];
         b[1] = board[0][1];
         b[2] = board[0][2];
         b[3] = board[1][0];
         b[4] = board[1][1];
         b[5] = board[1][2];
         b[6] = board[2][0];
         b[7] = board[2][1];
         b[8] = board[2][2];
         return b;
     }
     if(box == 2){
       b[0] = board[0][3];
       b[1] = board[0][4];
       b[2] = board[0][5];
       b[3] = board[1][3];
       b[4] = board[1][4];
       b[5] = board[1][5];
       b[6] = board[2][3];
       b[7] = board[2][4];
       b[8] = board[2][5];
       return b;  
     }
     if(box == 3){
       b[0] = board[0][6];
       b[1] = board[0][7];
       b[2] = board[0][8];
       b[3] = board[1][6];
       b[4] = board[1][7];
       b[5] = board[1][8];
       b[6] = board[2][6];
       b[7] = board[2][7];
       b[8] = board[2][8];
       return b; 
     }
     if(box == 4){
       b[0] = board[3][0];
       b[1] = board[3][1];
       b[2] = board[3][2];
       b[3] = board[4][0];
       b[4] = board[4][1];
       b[5] = board[4][2];
       b[6] = board[5][0];
       b[7] = board[5][1];
       b[8] = board[5][2];
       return b; 
     }
     if(box == 5){
       b[0] = board[3][3];
       b[1] = board[3][4];
       b[2] = board[3][5];
       b[3] = board[4][3];
       b[4] = board[4][4];
       b[5] = board[4][5];
       b[6] = board[5][3];
       b[7] = board[5][4];
       b[8] = board[5][5];
       return b;
     }
     if(box == 6){
       b[0] = board[3][6];
       b[1] = board[3][7];
       b[2] = board[3][8];
       b[3] = board[4][6];
       b[4] = board[4][7];
       b[5] = board[4][8];
       b[6] = board[5][6];
       b[7] = board[5][7];
       b[8] = board[5][8];
       return b;
     }
     if(box == 7){
       b[0] = board[6][0];
       b[1] = board[6][1];
       b[2] = board[6][2];
       b[3] = board[7][0];
       b[4] = board[7][1];
       b[5] = board[7][2];
       b[6] = board[8][0];
       b[7] = board[8][1];
       b[8] = board[8][2];
       return b;
     }
     if(box == 8){
       b[0] = board[6][3];
       b[1] = board[6][4];
       b[2] = board[6][5];
       b[3] = board[7][3];
       b[4] = board[7][4];
       b[5] = board[7][5];
       b[6] = board[8][3];
       b[7] = board[8][4];
       b[8] = board[8][5];
       return b;
     }
     if(box == 9){
       b[0] = board[6][6];
       b[1] = board[6][7];
       b[2] = board[6][8];
       b[3] = board[7][6];
       b[4] = board[7][7];
       b[5] = board[7][8];
       b[6] = board[8][6];
       b[7] = board[8][7];
       b[8] = board[8][8];
       return b;
     }
     return null;
 }
 
 /**
  * 
  * @param box- the specific 3x3 box you want to obtain
  * @param number- the number you want to obtain within the specified box
  * @return- true if the number is within the box, false otherwise 
  */
 public boolean contains(int box, char number){
     for(int i=0; i<9; i++){
         if(getSpecificBox(box)[i] == number){
             return true;
         }
     }
     return false;
 }
 
 /**
  * 
  * @param i- the x coordinate of a specific square within a 3x3 box
  * @param j- the y coordinate of a specific square within a 3x3 box
  * @return- an int, representing the box where the specific square is located in 
  */
 public int getBox(int i, int j){
     if(i <3 && j <3){
         return 1;
     }
     if(i < 3 && j > 2 && j < 6){
         return 2;
     }
     if(i < 3 && j > 5 && j < 9){
         return 3;
     }
     if(i > 2 && i < 6 && j < 3){
         return 4;
     }
     if(i > 2 && i < 6 && j > 2 && j < 6){
         return 5;
     }
     if(i > 2 && i < 6 && j > 5 && j < 9){
         return 6;
     }
     if(i > 5 && i < 9 && j < 3){
         return 7;
     }
     if(i > 5 && i < 9 && j > 2 && j < 6){
          return 8;
     }
     if(i > 5 && i < 9 && j > 5 && j < 9){
         return 9;
     }
     return -1;
 }
 
 /**
  * 
  * @param box- the specific box you want to obtain
  * @return- an ArrayList<Integer> that represents the open indices within a specific box
  */
 public ArrayList<Integer> getOpenSpotsInBox(int box){
     ArrayList<Integer> indices = new ArrayList<>();
     for(int i = 0; i<9; i++){
         for(int j = 0; j<9; j++){
             if(openSpot[i][j] && getBox(i, j) == box){
                indices.add(i);
                indices.add(j);
             }
         }
     }
     return indices;
 }
 
 public ArrayList<Integer> getOpenSpotsInRow(int row){
     ArrayList<Integer> indices = new ArrayList();
     for(int c = 0; c<9; c++){
         if(openSpot[row][c]){
             indices.add(row);
             indices.add(c);
         }
     }
     return indices;
 }
 
  public ArrayList<Integer> getOpenSpotsInColumn(int column){
     ArrayList<Integer> indices = new ArrayList();
     for(int r = 0; r<9; r++){
         if(openSpot[r][column]){
             indices.add(r);
             indices.add(column);
         }
     }
     return indices;
 }
 
 /**
  * Calculates and solves the Soduko board specified in the soduko.in file
     * @param track- determines when to track solved cells from a guessed markup
     * @throws java.lang.InterruptedException
     * @throws java.lang.reflect.InvocationTargetException
  */
 public void calculuateSodukoBoard(boolean track) throws InterruptedException, InvocationTargetException{
     int stop = 0;
     for(int i =1; i<=9; i++){
         evaluate(i, getOpenSpotsInBox(i), track);
         checkRows(track);
         checkColumns(track);
         SwingUtilities.invokeAndWait(new Runnable(){ //code to slow down or speed up the Soduko Solving algorithms
             @Override
             public void run() {
                 try {
                     Thread.sleep(JSlide.getTimerValue() * 100);
                 } 
                 catch (InterruptedException ex) {
                     System.out.println("Interrupted Exception: " + ex);
                 }
             }
         });
         super.repaint();
         if(!solvedAnyCells){
         stop++;
     }
         if(i == 9 && !isDone() && stop != 9){
             i = 0;
             stop = 0;
     }
         else if(i == 9 && !isDone() && stop == 9){
             if(track){
                 trackedCells.add(-1); //to distinguish different rounds, "colors"
             }
         }
 }
 }
 
 /**
  * Determines if the board is solved
  * @return- true if the board is solved (each square has a number in it) and false otherwise 
  */
 public boolean isDone(){
     for(int i=0; i<9; i++){
         for(int j = 0; j<9; j++){
             if(board[i][j] == 'b'){
                 return false;
             }
         }
     }
     System.out.println("Solved!");
     return true;
 }
 
 
 /**
  * checks the rows and puts a number into a square if it is a solution to the puzzle
  * @param track - determines when to track cells that are obtained from a guessed markup
  */
 public void checkRows(boolean track){
     boolean isUniqueSolution = true;
     int ind;
     char[] numbers = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
     int count = 0;
     int row = 0;
     while(getOpenSpotsInRow(row).isEmpty() && row < 8){
         row++;
     }
         for(int i = 0; i<getOpenSpotsInRow(row).size()-1; i+=2){
         if(!contains(getBox(getOpenSpotsInRow(row).get(i), getOpenSpotsInRow(row).get(i+1)), numbers[count])){
             board[getOpenSpotsInRow(row).get(i)][getOpenSpotsInRow(row).get(i+1)] = numbers[count];
             if(check(getOpenSpotsInRow(row).get(i), getOpenSpotsInRow(row).get(i+1))){
                 ind = 0;
                 while(ind < getOpenSpotsInRow(row).size()-1){
                     if(ind != i){
                     board[getOpenSpotsInRow(row).get(i)][getOpenSpotsInRow(row).get(i+1)] = 'b';
                     board[getOpenSpotsInRow(row).get(ind)][getOpenSpotsInRow(row).get(ind+1)] = numbers[count];
                     if(check(getOpenSpotsInRow(row).get(ind), getOpenSpotsInRow(row).get(ind+1))){
                         isUniqueSolution = false;
                     }
                     board[getOpenSpotsInRow(row).get(ind)][getOpenSpotsInRow(row).get(ind+1)] = 'b';
                     }
                     ind+=2;
                 }
                 if(isUniqueSolution){
                     int x = getOpenSpotsInRow(row).get(i);
                     int y = getOpenSpotsInRow(row).get(i+1);
                     board[getOpenSpotsInRow(row).get(i)][getOpenSpotsInRow(row).get(i+1)] = numbers[count];
                     checkedRows = true;
                     if(track){
                      trackedCells.add(x);
                      trackedCells.add(y);
                     }
                 }
                 isUniqueSolution = true; //reset to its default value
             }
             else{
               board[getOpenSpotsInRow(row).get(i)][getOpenSpotsInRow(row).get(i+1)] = 'b';  
             }
             if(board[getOpenSpotsInRow(row).get(i)][getOpenSpotsInRow(row).get(i+1)] != 'b'){
                 getOpenSpot()[getOpenSpotsInRow(row).get(i)][getOpenSpotsInRow(row).get(i+1)] = false;
             }
         }
         if(count < 8){
             count++;
             i = -2;
         }
         if(count == 8 && row == 8){
             break;
         }
         if(count == 8){
             count = 0;
             row++;
         }
     }
     }
 
 /**
  * checks the columns of the puzzle and puts in a number if it is a solution to a specific cell
  * @param track- determines if cells should be tracked from a guessed markup 
  */
 public void checkColumns(boolean track){
    boolean isUniqueSolution = true;
     int ind;
     char[] numbers = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
     int count = 0;
     int column = 0;
     while(getOpenSpotsInColumn(column).isEmpty() && column < 8){
         column++;
     }
         for(int i = 0; i<getOpenSpotsInColumn(column).size()-1; i+=2){
         if(!contains(getBox(getOpenSpotsInColumn(column).get(i), getOpenSpotsInColumn(column).get(i+1)), numbers[count])){
             board[getOpenSpotsInColumn(column).get(i)][getOpenSpotsInColumn(column).get(i+1)] = numbers[count];
             if(check(getOpenSpotsInColumn(column).get(i), getOpenSpotsInColumn(column).get(i+1))){
                 ind = 0;
                 while(ind < getOpenSpotsInColumn(column).size()-1){
                     if(ind != i){
                     board[getOpenSpotsInColumn(column).get(i)][getOpenSpotsInColumn(column).get(i+1)] = 'b';
                     board[getOpenSpotsInColumn(column).get(ind)][getOpenSpotsInColumn(column).get(ind+1)] = numbers[count];
                     if(check(getOpenSpotsInColumn(column).get(ind), getOpenSpotsInColumn(column).get(ind+1))){
                         isUniqueSolution = false;
                     }
                     board[getOpenSpotsInColumn(column).get(ind)][getOpenSpotsInColumn(column).get(ind+1)] = 'b';
                     }
                     ind+=2;
                 }
                 if(isUniqueSolution){
                     int x = getOpenSpotsInColumn(column).get(i);
                     int y = getOpenSpotsInColumn(column).get(i+1);
                     board[getOpenSpotsInColumn(column).get(i)][getOpenSpotsInColumn(column).get(i+1)] = numbers[count];
                     checkedColumns = true;
                     if(track){
                       trackedCells.add(x);
                       trackedCells.add(y);
                     }
                 }
                 isUniqueSolution = true; //reset to its default value
             }
             else{
               board[getOpenSpotsInColumn(column).get(i)][getOpenSpotsInColumn(column).get(i+1)] = 'b';  
             }
             if(board[getOpenSpotsInColumn(column).get(i)][getOpenSpotsInColumn(column).get(i+1)] != 'b'){
                 openSpot[getOpenSpotsInColumn(column).get(i)][getOpenSpotsInColumn(column).get(i+1)] = false;
             }
         }
         if(count < 8){
             count++;
             i = -2;
         }
         if(count == 8 && column == 8){
             break;
         }
         if(count == 8){
             count = 0;
             column++;
         }
     } 
 }
 
 /**
  * The main method that computes the solution to the puzzle
  * @param box- the specific box you want to obtain
  * @param indices - the indices of the open spots within the specified box
  * @param keepTrack - determines if solved cells should be tracked from a guessed markup
  */
 public void evaluate(int box, ArrayList<Integer> indices, boolean keepTrack){
     int count = 0;
     char[] numbers = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
     outer: for(int i = 0; i<numbers.length; i++){
  if(!contains(box, numbers[i])){
      index = count; //0 initially
      if(count > indices.size()-1){  
          return;
      }
      board[indices.get(count)][indices.get(++count)] = numbers[i];
        if(check(indices.get(count-1), indices.get(count))){
          board[indices.get(index)][indices.get(index+1)] = 'b';
          count = 0;
          while(count < indices.size()-1){
              if(count == index){
                  count+=2;
                  continue;
              }
              board[indices.get(count)][indices.get(++count)] = numbers[i];
              if(check(indices.get(count-1), indices.get(count))){
                  board[indices.get(index)][indices.get(index+1)] = 'b';
                  isAUniqueSolution = false;
                  break;
              }
              count++;
          }
          if(isAUniqueSolution){
              cellsSolved++;
              board[indices.get(index)][indices.get(index+1)] = numbers[i];
              if(keepTrack){
                  trackedCells.add(indices.get(index));
                  trackedCells.add(indices.get(index+1));
              }
          }
      }
      else{
         board[indices.get(count-1)][indices.get(count)] = 'b'; 
         i = i-1;
         count++;
         if(i == 7 && count == indices.size()-1){
             break;
         }
         continue;
      }
  }
  else{
     continue;
  }
      for(int m = 0; m<indices.size()-1; m+=2){ //reset all other spots to be blank again
          if(m != index){
          board[indices.get(m)][indices.get(m+1)] = 'b';
  }
      }
      isAUniqueSolution = true; //reset to its default value
  if(board[indices.get(index)][indices.get(index+1)] != 'b'){
      openSpot[indices.get(index)][indices.get(index+1)] = false;
      indices.remove(index);
      indices.remove(index);
      for(int n = 0; n<indices.size()-1; n+=2){
          if(openSpot[indices.get(n)][indices.get(n+1)]){
              count = n;
              break;
          }
      }
  }
  else{
      for(int n = 0; n<indices.size()-1; n+=2){
          if(openSpot[indices.get(n)][indices.get(n+1)]){
              count = n;
              break;
          }
      }
  }
 }
     if(cellsSolved != 0){
         solvedAnyCells = true;
         cellsSolved = 0;
     }
     else{
         solvedAnyCells = false;
         cellsSolved = 0;
     }
 }
 
 /**
  * Determines if a square with a specific number is a valid position under the rules of Soduko
  * @param i- the x coordinate of a specific square
  * @param j- the y coordinate of a specific square
  * @return - true if the square with a specific number is a valid position and false otherwise
  */
 public static boolean check(int i, int j){
     for(int k = 0; k<9; k++){
         if(k != j){
         if(board[i][j] == board[i][k]){
             board[i][j] = 'b';
             break;
         }
         }
     }
     if(board[i][j] == 'b'){
         return false;
     }
     for(int k = 0; k<9; k++){
         if(k != i){
         if(board[i][j] == board[k][j]){
             board[i][j] = 'b';
             break;
         }
         }
     }
     return (board[i][j] != 'b');
 }
 
 /**
  * Marks up all the empty cells in the Soduko puzzle
  */
 public void markupEmptyCells(){
     int count = 0;
     char[] numbers = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
     for(int i=1; i<=9; i++){
         for(int j=0; j<getOpenSpotsInBox(i).size()-1; j+=2){
            while(count < 9){
                if(!contains(i, numbers[count])){
                board[getOpenSpotsInBox(i).get(j)][getOpenSpotsInBox(i).get(j+1)] = numbers[count];
      if(check(getOpenSpotsInBox(i).get(j) , getOpenSpotsInBox(i).get(j+1))){
          if(markupBoard[getOpenSpotsInBox(i).get(j)][getOpenSpotsInBox(i).get(j+1)] != null){
          markupBoard[getOpenSpotsInBox(i).get(j)][getOpenSpotsInBox(i).get(j+1)] = 
          markupBoard[getOpenSpotsInBox(i).get(j)][getOpenSpotsInBox(i).get(j+1)].concat(String.valueOf(numbers[count]));
      }
          else{
            markupBoard[getOpenSpotsInBox(i).get(j)][getOpenSpotsInBox(i).get(j+1)] = String.valueOf(numbers[count]);
          }
      }
     }
      count++;
      board[getOpenSpotsInBox(i).get(j)][getOpenSpotsInBox(i).get(j+1)] = 'b';
            }
            count = 0;
 }
     }
}
 
 /**
  * 
  * @param s- the string that you want find all the substrings of 
  * @return- an ArrayList of type String that contains all the substring of the specified string s 
  */
 public ArrayList<String> allSubStrings(String s){
     ArrayList<String> allSubs = new ArrayList<>();
        String sub;
        if(s != null){
        for(int i = 0 ; i < s.length(); i++ ){
         for(int j = 1 ; j <= s.length()-i; j++ ){
            sub = s.substring(i, i+j);
            allSubs.add(sub);
         }
    }
        }
        return allSubs;
    }
 
 /**
  * 
  * @param i- row of the puzzle
  * @param j- column of the puzzle
  * @param sets - the preemptive sets of each row 
  */
 public void deleteRowCellMarkups(int i, int j, ArrayList<Integer> sets){
         int k = i;
         outer: for(int l =0; l<9; l++){
          if(markupBoard[k][l] != null){
              for(int c = 1; c<sets.size(); c+=2){
                     if(l == sets.get(c)){
                         continue outer;
                     }
                 }
             for(int m=0; m<allSubStrings(markupBoard[k][l]).size(); m++){
               if(allSubStrings(markupBoard[i][j]).contains(allSubStrings(markupBoard[k][l]).get(m))){
                 markupBoard[k][l] = markupBoard[k][l].replace(allSubStrings(markupBoard[k][l]).get(m), "");
             }
         }
             }
     }
 }
 
 /**
  * 
  * @param i- row of the puzzle 
  * @param j - column of the puzzle
  * @param sets- the preemptive sets of each column
  */
 public void deleteColumnCellMarkups(int i, int j, ArrayList<Integer> sets){
         int k = j;
         outer: for(int l =0; l<9; l++){
          if(markupBoard[l][k] != null){
              for(int c = 0; c<sets.size(); c+=2){
                     if(l == sets.get(c)){
                         continue outer;
                     }
                 }
             for(int m=0; m<allSubStrings(markupBoard[l][k]).size(); m++){
             if(allSubStrings(markupBoard[i][j]).contains(allSubStrings(markupBoard[l][k]).get(m))){
                 markupBoard[l][k] = markupBoard[l][k].replace(allSubStrings(markupBoard[l][k]).get(m), "");
             }
         }
             }
     }
 }
 
 /**
  * 
  * @param i- row of the puzzle
  * @param j- column of the puzzle
  * @param sets - the preemptive sets of each box
  */
 public void deleteBoxCellMarkups(int i, int j, ArrayList<Integer> sets){
     int box = getBox(i,j);
     for(int k = 0; k<9; k++){
         outer: for(int l = 0; l<9; l++){
       if(markupBoard[k][l] != null && allSubStrings(markupBoard[k][l]).size() > 0 && getBox(k,l) == box){
           for(int c = 0; c<sets.size()-1; c+=2){
                     if(k == sets.get(c) && l == sets.get(c+1)){
                         continue outer;
                     }
                 }
          for(int m=0; m<allSubStrings(markupBoard[k][l]).size(); m++){
              if(allSubStrings(markupBoard[i][j]).contains(allSubStrings(markupBoard[k][l]).get(m))){
                markupBoard[k][l] = markupBoard[k][l].replace(allSubStrings(markupBoard[k][l]).get(m), "");  
              }
          }
       }      
     }
 }
 }
 
 /**
  * 
  * @param i- row 
  * @param j- column
  * @return- an ArrayList of type Integer that is the preemptive sets of each row
  */
 public ArrayList<Integer> findRowPreemptiveSets(int i, int j){
     ArrayList<Integer> rowPreemptiveSets = new ArrayList<>();
     String value = markupBoard[i][j];
         int k = i;
         for(int l = 0; l<9; l++){
             if(markupBoard[k][l] != null){
      //used to be if((markupBoard[k][l].equals(value) || allSubStrings(value).contains(markupBoard[k][l])) && k == i)
           if(markupBoard[k][l].equals(value)){
               rowPreemptiveSets.add(k);
               rowPreemptiveSets.add(l);
           }  
             }
     }
     if(markupBoard[i][j].length() == rowPreemptiveSets.size() / 2){
     return rowPreemptiveSets;
 }
     else{
         rowPreemptiveSets.clear();
         return rowPreemptiveSets;
     }
 }
 
 /**
  * 
  * @param i- row
  * @param j- column
  * @return- an ArrayList of type Integer that is the preemptive sets of each column 
  */
 public ArrayList<Integer> findColumnPreemptiveSets(int i, int j){
     ArrayList<Integer> columnPreemptiveSets = new ArrayList<>();
     String value = markupBoard[i][j];
         int k = j;
         for(int l =0; l<9; l++){
             if(markupBoard[l][k] != null){
                 if(markupBoard[l][k].equals(value)){
                  columnPreemptiveSets.add(l);
                  columnPreemptiveSets.add(k);
                 }
             }
         }
     if(markupBoard[i][j].length() == columnPreemptiveSets.size() / 2){
     return columnPreemptiveSets;
 }
     else{
         columnPreemptiveSets.clear();
         return columnPreemptiveSets;
     }
 }
 
 /**
  * 
  * @param i- row
  * @param j- column
  * @return - an ArrayList of type Integer that is the preemptive sets of each box
  */
 public ArrayList<Integer> findBoxPreemptiveSets(int i, int j){
     int box = getBox(i,j);
     ArrayList<Integer> boxPreemptiveSets = new ArrayList<>();
     String value = markupBoard[i][j];
     for(int k = 0; k<9; k++){
         for(int l = 0; l<9; l++){
             if(markupBoard[k][l] != null){
     //used to be if((markupBoard[k][l].equals(value) || allSubStrings(value).contains(markupBoard[k][l])) && getBox(k,l) == box)
                 if(markupBoard[k][l].equals(value) && getBox(k,l) == box){
                     boxPreemptiveSets.add(k);
                     boxPreemptiveSets.add(l);
                 }
             }
         }
     }
     if(markupBoard[i][j].length() == boxPreemptiveSets.size() / 2){
     return boxPreemptiveSets;
 }
     else{
         boxPreemptiveSets.clear();
         return boxPreemptiveSets;
     }
 }
 
 /**
  * determines the preemptive sets of the soduko puzzle and tries to solve as many cells as it can 
  * by deleting the markup of cells
  */
 public void findPreemptiveSets(){
     for(int i=0; i<9; i++){
         for(int j = 0; j<9; j++){
      if(markupBoard[i][j] != null){
          if(findRowPreemptiveSets(i,j).size() > 2){
              deleteRowCellMarkups(i, j, findRowPreemptiveSets(i,j));
          }
      }
      if(markupBoard[i][j] != null){
          if(findColumnPreemptiveSets(i,j).size() > 2){
              deleteColumnCellMarkups(i,j, findColumnPreemptiveSets(i,j));
          }
      }
      if(markupBoard[i][j] != null){
          if(findBoxPreemptiveSets(i,j).size() > 2){
             deleteBoxCellMarkups(i,j, findBoxPreemptiveSets(i,j)); 
          }
      }
     }
     }
     for(int i=0; i<9; i++){
         for(int j = 0; j<9; j++){
            if(markupBoard[i][j] != null && (markupBoard[i][j].length() == 1 || board[i][j] != 'b')){ 
                board[i][j] = markupBoard[i][j].charAt(0);
                markupBoard[i][j] = null;
                openSpot[i][j] = false;
     }
            if(board[i][j] == 'b'){    //CHANGED!
                openSpot[i][j] = true;
            }
 }
 }
}
 
 /**
  * determines if a soduko board is a valid board
  * @return- true if it is a valid board, false otherwise 
  */
 public boolean isAValidBoard(){
     int count = 0;
     int box = 1;
     int isValid = 0;
     char[] numbers = {'1','2','3','4','5','6','7','8','9'};
     
     
     for(int i=0; i<9; i++){
         for(int j=0; j<9; j++){
             if(board[i][j] != 'b'){
             if(!check(i,j)){
               return false;  
             }
             }
     }
     }
     while(getOpenSpotsInBox(box).isEmpty()){
         box++;
     }
         for(int j =0; j<getOpenSpotsInBox(box).size()-1; j+=2){
                      if(!contains(box, numbers[count])){
                          board[getOpenSpotsInBox(box).get(j)][getOpenSpotsInBox(box).get(j+1)] = numbers[count];
                          if(!check(getOpenSpotsInBox(box).get(j), getOpenSpotsInBox(box).get(j+1))){
                              isValid++;
                      }
                          board[getOpenSpotsInBox(box).get(j)][getOpenSpotsInBox(box).get(j+1)] = 'b';
                          if(isValid == getOpenSpotsInBox(box).size() / 2){
                              return false;
                          }
         }
                      else{
                          count++;
                          isValid = 0;
                          j = -2;
                      }
                      if(count == 9){
                          box++;
                          count = 0;
                          isValid = 0;
                          j = -2;
                          }
                      if(box == 10){
                          break;
                      }
                      if(j == getOpenSpotsInBox(box).size()-2 && count <8){
                          j = -2;
                          count++;
                          isValid = 0;
                      }
                      else if(j == getOpenSpotsInBox(box).size()-2 && count >7 && box<9){
                          j = -2;
                          count = 0;
                          isValid = 0;
                          box++;
                      }
                      while(getOpenSpotsInBox(box).isEmpty()){
                          box++;
                      }
         }
     return true;
 }
 
 /**
  * determines if an empty cell has an empty markup
  * @return- true if it does have an empty markup, and false otherwise
  */
 public boolean hasEmptyMarkup(){
     for(int i=0; i<9; i++){
         for(int j=0; j<9; j++){
            if(board[i][j] == 'b' && markupBoard[i][j] == null){
                //System.out.println(i);
                //System.out.println(j);
                return true;
            } 
         }
 }
     return false;
 }
 
 /**
  * determines the biggest markup length
  * @return- the biggest markup length
  */
 public int biggestMarkupLength(){
     int largest = 0;
     for(int i =0; i<9; i++){
         for(int j = 0; j<9; j++){
             if(markupBoard[i][j] != null && markupBoard[i][j].length() > largest){
               largest = markupBoard[i][j].length();
             }
         }
     }
     return largest;
 }
 
 /**
  * updates the markups of each cell after a call to calculateSodukoBoard
  */
 public void updateMarkups(){
     for(int i=0; i<9; i++){
         for(int j = 0; j<9; j++){
            if(markupBoard[i][j] != null && (markupBoard[i][j].length() == 1 || board[i][j] != 'b')){
                markupBoard[i][j] = null;
            } 
         }
     }
 }
 
 /**
  * sorts each markup from smallest markup (in string length) to biggest markup
  * @return- an ArrayList of type Integer that is all the markups of the puzzle 
  */
 public ArrayList<Integer> sortMarkups(){
     int count = 2;
     ArrayList<Integer> markupIndices = new ArrayList<>();
     if(count <= biggestMarkupLength()){
     for(int i=0; i<9; i++){
         for(int j=0; j<9; j++){
             if(markupBoard[i][j] != null && markupBoard[i][j].length() == count){
             markupIndices.add(i);
             markupIndices.add(j);
         }
             if(i == 8 && j == 8 && count < biggestMarkupLength()){
             i = -1;
             count++;
         }
             }
         }
     }
     return markupIndices;
 }
 
 /**
  * 
  * @param i- row
  * @param j- column
  * @param originalMarkup- a string containing the original markup of a cell 
  */
 public void resetTrackedCells(int i, int j, String originalMarkup){
     int tracked = 0;
     board[i][j] = 'b';
     openSpot[i][j] = true;
     markupBoard[i][j] = originalMarkup;
     for(int m=0; m<trackedCells.size(); m+=2){
         if(trackedCells.get(tracked) != -1){
         board[trackedCells.get(tracked)][trackedCells.get(tracked+1)] = 'b';
         openSpot[trackedCells.get(tracked)][trackedCells.get(tracked+1)] = true;
         trackedCells.remove(tracked);
         trackedCells.remove(tracked);
     }
         else{
             trackedCells.remove(tracked);
             break;
         }
         m= -2;
     }
 }
 
 /**
  * this method solves the rest of the soduko puzzle, if it was not all solved by the calculateSodukoBoard method
     * @throws java.lang.InterruptedException
     * @throws java.lang.reflect.InvocationTargetException
  */
 public void evaluateSmallestMarkup() throws InterruptedException, InvocationTargetException{
     outer: for(int i=0; i<sortMarkups().size()-1; i+=2){
         for(int j = 0; j<markupBoard[sortMarkups().get(i)][sortMarkups().get(i+1)].length(); j++){
         board[sortMarkups().get(i)][sortMarkups().get(i+1)] = markupBoard[sortMarkups().get(i)][sortMarkups().get(i+1)].charAt(j);
         openSpot[sortMarkups().get(i)][sortMarkups().get(i+1)] = false;
         int x = sortMarkups().get(i); //need to do this because you changed the findPreemptiveSets methods
         int y = sortMarkups().get(i+1); //need to do this because you changed the findPreemptiveSets methods
         String originalMarkup = markupBoard[sortMarkups().get(i)][sortMarkups().get(i+1)];
         markupBoard[x][y] = null;
         findPreemptiveSets();
         calculuateSodukoBoard(true);
         if(isDone()){
             return;
         }
         if(!hasEmptyMarkup() && isAValidBoard()){
             updateMarkups();
           break;  
         }
         while(hasEmptyMarkup() || !isAValidBoard()){
             resetTrackedCells(x, y, originalMarkup);
         }
         }
         }
 }
}

 
