
package soduko;

import java.util.Hashtable;
import javax.swing.JLabel;
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
    private static int timerValue = 23; //the initial value the JSlider will be at
    
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
       setValue(timerValue);
       Hashtable labels = new Hashtable();
       labels.put(0, new JLabel("Fast"));
       labels.put(max/3, new JLabel("Slow"));
       labels.put(max, new JLabel("Very Slow"));
       setLabelTable(labels);
       setPaintLabels(true); //makes the JSlider labels visible
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
