/**
 * This code creates an area to draw and sets some colors to implement
 * This class should be called in the Layout class
 * 
 * RESOURCES FOR THIS:
 *      https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics2D.html
 *      https://docs.oracle.com/javase/8/docs/api/java/awt/Component.html#addMouseMotionListener-java.awt.event.MouseMotionListener-
 *      https://docs.oracle.com/javase/8/docs/api/java/awt/Component.html#addMouseListener-java.awt.event.MouseListener-
 *      https://docs.oracle.com/javase/8/docs/api/java/awt/Component.html#repaint-- 
 *      http://underpop.online.fr/j/java/help/colors-graphics-programming.html.gz 
 *      https://zetcode.com/javaswing/painting/ 
 */

import javax.swing.JComponent;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class DrawingArea extends JComponent {

    // Image to draw
    private Image image;

    // Graphics2D object - placeholder for the area to draw on
    private Graphics2D graphics2dObject;

    // Mouse coordinates for to track the drawing
    private int currentX;
    private int currentY;
    private int previousX;
    private int previousY;
    
    public DrawingArea() {

        // isDoubleBuffered is enabled by default in JPanel. So I set it to False.
        setDoubleBuffered(false);

        // adding the mouse listener here with a new MouseAdapter to check if mouse is pressed and
        // save the coordinates of the mouse
        addMouseListener(new MouseAdapter() { public void mousePressed(MouseEvent event) 
            {
            // get and save the coordinates x and y when mouse is pressed
            previousX = event.getX();
            previousY = event.getY();
            }
        });
    
        // adding the motion listener here in case mouse is dragged
        // save the coordinates of the mouse
        addMouseMotionListener(new MouseMotionAdapter() { public void mouseDragged(MouseEvent event) {

            // get the coordinates when x and y when mouse is dragged
            currentX = event.getX();
            currentY = event.getY();
        
                if (graphics2dObject != null) {

                    // makes it able to draw a line if graphics2dObject context is not null
                    graphics2dObject.drawLine(previousX, previousY, currentX, currentY);

                    // clears/refreshes the area to repaint
                    repaint();

                    // store current coordinates of x and y as previous(old) x and y
                    previousX = currentX;
                    previousY = currentY;
                }
            }
        });
    }
    
    // Creating a null image to draw
    protected void paintComponent(Graphics graphics) {
        if (image == null) {
            
            // we create null image to draw
            image = createImage(getSize().width, getSize().height);
            graphics2dObject = (Graphics2D) image.getGraphics();

            // to add greater realism to a digital image by smoothing jagged edges on curved lines and diagonals.
            graphics2dObject.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // clear the drawing draw area
            clear();
        }
    
        // to draw the image
        graphics.drawImage(image, 0, 0, null);
    }
    
    // Here I create the methods for the colors 
    public void clear() {
        graphics2dObject.setPaint(Color.white);

        // this clears the area by drawing the area all white
        graphics2dObject.fillRect(0, 0, getSize().width, getSize().height);
        graphics2dObject.setPaint(Color.black);
        repaint();
    }
    
    public void red() {
        // to draw red on graphics2dObject 
        graphics2dObject.setPaint(Color.RED);
    }

    public void green() {
        // to draw green on graphics2dObject 
        graphics2dObject.setPaint(Color.GREEN);
    }

    public void blue() {
        // to draw blue on graphics2dObject 
        graphics2dObject.setPaint(Color.BLUE);
    }
    
    public void black() {
        // to draw black on graphics2dObject 
        graphics2dObject.setPaint(Color.BLACK);
    }

    public void orange() {
        // to draw orange on graphics2dObject 
        graphics2dObject.setPaint(Color.ORANGE);
    }

    public void lightGray() {
        // to draw light gray on graphics2dObject 
        graphics2dObject.setPaint(Color.LIGHT_GRAY);
    }

    public void yellow() {
        // to draw yellow on graphics2dObject 
        graphics2dObject.setPaint(Color.YELLOW);
    }

    public void magenta() {
        // to draw magenta on graphics2dObject 
        graphics2dObject.setPaint(Color.MAGENTA);
    }

    public void cyan() {
        // to draw cyan on graphics2dObject 
        graphics2dObject.setPaint(Color.CYAN);
    }

    public void darkGray() {
        // to draw dark gray on graphics2dObject 
        graphics2dObject.setPaint(Color.DARK_GRAY);
    }
}