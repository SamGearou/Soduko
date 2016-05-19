
package soduko;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;






public class Soduko extends JFrame{
    private static int XCoord = 20; //the initial x coordinate for the 81 boxes
    private static int YCoord = 50; //the initial y coordinate for the 81 boxes
    private static final int height = 40; //the height of each box
    private static final int width = 40; //the width of each box
    private static Parse obj  = new Parse(); //Parse object to call parseFile() method
    private static char[][] board = new char[9][9]; //the soduko board
    private static boolean[][] openSpot = new boolean[9][9]; //determines which locations of the soduoko board are empty
    private int index; //an int that is used in calculating the Soduko solution in the evaluate method
    private boolean isAUniqueSolution = true; //determines if a location in a specifix 3x3 box is a unique solution
    
    /**
     * Soduko constructor initalizes the JFrame and JPanel
     */
    public Soduko(){
        super("Soduko Solver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        JPanel panel = new JPan();
        panel.setBackground(Color.RED);
        panel.setPreferredSize(new Dimension(600,600));
        setContentPane(panel);
        pack();
      }
    
    /**
     * Reads in data from a file and calls the calculateSodukoBoard method to solve the puzzle
     * @param args- the command line arguments
     * @throws IOException 
     */
 public static void main(String[] args) throws IOException{
     Soduko sod = new Soduko();
     obj.parseFile();
     for(int i=0; i<9; i++){
         for(int j = 0; j<9; j++){
           board[i][j] = obj.getFileContents()[i].charAt(j);
           if(board[i][j] == 'b'){
               openSpot[i][j] = true;
           }
           else{
               openSpot[i][j] = false;
           }
         }
     }
     sod.calculuateSodukoBoard();
 }
 
 /**
  * 
  * @return- returns XCoord, an int 
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
 
 /**
  * Calculates and solves the Soduko board specified in the soduko.in file
  */
 public void calculuateSodukoBoard(){
     for(int i =1; i<=9; i++){
         evaluate(i, getOpenSpotsInBox(i));
         if(i == 9 && !isDone()){
             i = 0;
     }
         if(isDone()){
             super.repaint(); 
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
     return true;
 }
 
 /**
  * The main method that computes the solution to the puzzle
  * @param box- the specific box you want to obtain
  * @param indices - the indices of the open spots within the specified box
  */
 public void evaluate(int box, ArrayList<Integer> indices){
     int count = 0;
     char[] numbers = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
     outer: for(int i = 0; i<numbers.length; i++){
  if(!contains(box, numbers[i])){
      index = count; //0 initially
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
              board[indices.get(index)][indices.get(index+1)] = numbers[i];
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
}
