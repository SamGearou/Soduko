
package soduko;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Class that implements the JSlider
 * @author Sam Gearou
 * @date June 2, 2016
 */
public class JSlide extends JSlider implements ChangeListener {
    private final int min = 0; //the min value the JSlider can be
    private final int max = 70; //the max value the JSlider can be
    private static int timerValue = 10; //the initial value the JSlider will be at
    
    /**
     * Constructor- Sets up the JSlider
     */
    public JSlide(){
       setMajorTickSpacing(10);
       setMinorTickSpacing(1);
       setPaintTicks(true); //allows the tick marks to be visible
       setOrientation(JSlider.HORIZONTAL);
       setMinimum(min);
       setMaximum(max);
       setValue(20);
    }

    @Override
    /**
     * Sets the timerValue to be the value that the JSlider is currently at, 
     * in order to update this value so the Soduko algorithms slow down or speed up as indicated
     * based on the movement of the JSlider
     */
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        //System.out.println(source.getValue());
        setTimerValue(source.getValue());
    }
    
    /**
     * returns the value that the JSlider is currently at
     * @return - the timerValue 
     */
    public static int getTimerValue(){
        return timerValue;
    }
    
    /**
     * Sets the value of the timerValue
     * @param timer - an int representing the new value of the timerValue
     */
    public void setTimerValue(int timer){
        timerValue = timer;
    }
}
