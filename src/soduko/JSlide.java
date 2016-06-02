
package soduko;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class JSlide extends JSlider implements ChangeListener {
    private final int min = 0;
    private final int max = 40;
    private static int timerValue = 20;
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
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        //System.out.println(source.getValue());
        setTimerValue(source.getValue());
    }
    
    public static int getTimerValue(){
        return timerValue;
    }
    
    public void setTimerValue(int timer){
        timerValue = timer;
    }
}
