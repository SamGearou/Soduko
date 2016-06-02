
package soduko;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class JPan extends JPanel {
    /**
     * Constructor that initializes the JPanel
     */
    public JPan(){
      setLayout(new BorderLayout());
    }
    
    @Override
    /**
     * Draws the Soduko board as well as the numbers inside each square
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Soduko.setXCoord(20);
        Soduko.setYCoord(50);
        g.setColor(Color.GREEN);
    for(int i=0; i<9; i++){
        if(i == 3 || i ==4 || i == 5){
            g.setColor(Color.BLUE);
        }
        else{
            g.setColor(Color.GREEN);
        }
        for(int j = 0; j<9; j++){
            if(j == 3 && i!= 3 && i != 4 && i != 5){
                g.setColor(Color.BLUE);
            }
            if(j == 3 && (i== 3 || i == 4 || i == 5)){
                g.setColor(Color.GREEN);
            }
            if(j == 6 && i!= 3 && i != 4 && i != 5){
                g.setColor(Color.GREEN);
            }
            if(j == 6 && (i == 3 || i == 4 || i == 5)){
                g.setColor(Color.BLUE);
            }
          g.drawRect(Soduko.getXCoord(), Soduko.getYCoord(), Soduko.getMyWidth(), Soduko.getMyHeight());
          if(j != 8){
              Soduko.setXCoord(Soduko.getXCoord()+41);
          }
          if(j == 8){
              Soduko.setYCoord(Soduko.getYCoord()+41);
              Soduko.setXCoord(20);
          }
        }
    }
    Soduko.setXCoord(38);
    Soduko.setYCoord(73);
    g.setColor(Color.BLACK);
    for(int i = 0; i<9; i++){
        for(int j = 0; j<9; j++){
            if(Soduko.getBoard()[i][j] != 'b'){
            g.drawString(String.valueOf(Soduko.getBoard()[i][j]), Soduko.getXCoord(), Soduko.getYCoord());
            }
            if(j != 8){
                Soduko.setXCoord(Soduko.getXCoord()+41);
            }
            if(j == 8){
                Soduko.setYCoord(Soduko.getYCoord()+41);
                Soduko.setXCoord(38);
            }
    }
}
}
}
