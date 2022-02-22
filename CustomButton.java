/**
 * This class is to be a template for us to create our buttons
 *
 *
 * @author: Fehmi Neffati
 *
 */


import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {

    private static final long serialVersionUID = 1L;

    public CustomButton(String text, int width, int height, Color backColor, Color textColor){

        // text is what will the button display
        super(text);
        // size of the button
        this.setPreferredSize(new Dimension(width, height));
        // background color
        this.setBackground(backColor);
        // foreground color (when the button gets clicked)
        this.setForeground(textColor);

        this.setOpaque(true); 
        this.setBorderPainted(false);
    }
}
