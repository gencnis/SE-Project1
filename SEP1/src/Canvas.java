import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Canvas extends JPanel {

    private static final long serialVersionUID = 1L;

    // background color basically
    private Color backColor;
    // the color of the "brush
    private Color penColor;
    // coordinates of points
    private int pointX, pointY, pointR;

    // array containing the grid
    private ArrayList<Point> pointList;

    /**
     * this class instantiates the canvas area and sets the background to white and the brush to black
     */

    Canvas() {
        this.addMouseListener(new MouseEventHandler());
        this.addMouseMotionListener(new MouseMotionHandler());

        backColor = new Color(250, 250, 250);
        penColor = new Color(0, 0, 0);
        this.setBackground(backColor);

        pointList = new ArrayList<>();
        pointR = 5;
    }



    public void paintComponent(Graphics graphic){
        super.paintComponent(graphic);
        for(Point point: pointList){
            point.drawPoint(graphic);
        }
    }

    /**
     * when the users updates the color, we use this to update the brush color
     * @param value is an RGB value.
     */
    public void setPenColor (Color value){
        this.penColor = value;
    }

    /**
     * For updating the brush radius
     * @param value radius value
     */
    public void setPointRadius ( int value){
        this.pointR = value;
    }

    /**
     * to reset the canvas to white
     */
    public void resetArea () {
        backColor = new Color(255, 255, 255);
        penColor = new Color(0, 0, 0);
        pointList.clear();
        repaint();
    }

    private class MouseEventHandler implements MouseListener {

        // when the user clicks the mouse
        public void mousePressed(MouseEvent e) {
            // we get the x coordinate
            pointX = e.getX() - (pointR / 2);
            // and the y coordinate
            pointY = e.getY() - (pointR / 2);
            // we add them to the list of points that have been clicked and need repainting
            pointList.add(new Point(pointX, pointY, pointR, penColor));
            // and we "update their color" by repainting them
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {;}

        @Override
        public void mouseClicked(MouseEvent e) {;}

        @Override
        public void mouseEntered(MouseEvent e) {;}

        @Override
        public void mouseExited(MouseEvent e) {;}
    }
    private class MouseMotionHandler implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {
            // we get the x coordinate
            pointX = e.getX() - (pointR / 2);
            // and the y coordinate
            pointY = e.getY() - (pointR / 2);
            // we add them to the list of points that have been clicked and need repainting
            pointList.add(new Point(pointX, pointY, pointR, penColor));
            // and we "update their color" by repainting them
            repaint();
        }

        @Override
        public void mouseMoved(MouseEvent e) {;}
    }
}
