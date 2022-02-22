import javax.swing.*;

public class CustomSlider extends JSlider {

    private static final long serialVersionUID = 1l;

    public CustomSlider(int min, int max, int value, int majorTick, int minorTick, boolean vertical){

        // set the min, max, and starting value
        super(min, max, value);
        this.setMajorTickSpacing(majorTick);
        this.setMinorTickSpacing(minorTick);
        this.setOrientation((vertical) ? JSlider.VERTICAL: JSlider.HORIZONTAL);
    }
}
