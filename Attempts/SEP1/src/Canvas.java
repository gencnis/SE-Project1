
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;

public class Canvas extends JPanel  {

    private static final long serialVersionUID = 1L;

    // background color basically
    private Color backColor;
    // the color of the "brush
    private Color penColor;
    // coordinates of points
    private int pointX, pointY, pointR;
    //
    HashMap<String, Integer[]> textCoordinates;


    // array containing the grid
    private ArrayList<Point> pointList;

    ArrayList<AllShapes> shapes;
    ArrayList<String> allText;


    /**
     * this class instantiates the canvas area and sets the background to white and the brush to black
     */

    Canvas()
    {
        this.addMouseListener(new MouseEventHandler());
        this.addMouseMotionListener(new MouseMotionHandler());

        backColor = new Color(250, 250, 250);
        penColor = new Color(0, 0, 0);
        this.setBackground(backColor);

        pointList = new ArrayList<>();
        pointR = 5;
        this.shapes = new ArrayList<>();
        this.allText = new ArrayList<>();
        this.textCoordinates = new HashMap<>();
    }

    public void paintComponent(Graphics graphic)
    {
        super.paintComponent(graphic);


        for(Point point: pointList){
            point.drawPoint(graphic);
            //graphic.fillOval(point.getPointX(), point.getPointY(), point.getPointR(), point.getPointR());
        }
        Graphics2D g2 = (Graphics2D) graphic;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (AllShapes s : shapes) {
            g2.setPaint(penColor);
            s.draw();
        }
        for (String s : allText) {
            g2.setPaint(penColor);
            g2.drawString(s, textCoordinates.get(s)[0], textCoordinates.get(s)[1]);
        }
        }


    void restoreLast(){
        String path = "default.jpeg";
        Toolkit t = Toolkit.getDefaultToolkit();
        Image i = t.getImage(path);
        this.getGraphics().drawImage(i, 0,0,this);
        paintComponent(i.getGraphics());

    }


    public void line()
    {
        int size = pointList.size();
        int x1   = pointList.get(size-1).getPointX();
        int x2   = pointList.get(size-2).getPointX();
        int y1   = pointList.get(size-1).getPointY();
        int y2   = pointList.get(size-2).getPointY();
        AllShapes a = new AllShapes(this, new Line2D.Double(x1,y1,x2,y2));
        shapes.add(a);
        paintComponent(getGraphics());
        System.out.println("LINE SHOULD BE DRAWN");
    }

    public void text(String input, int x, int y)
    {
        allText.add(input);
        Integer[] intCoo = new Integer[2];
        intCoo[0] = x;
        intCoo[1] = y;
        textCoordinates.put(input, intCoo);
        getGraphics().drawString(input, x,y);
        paintComponent(getGraphics());
        System.out.println("words SHOULD BE written");
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
        shapes.clear();
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
            // If I do repaint, no flicks, but lines disappear and appear randomly
            //repaint();
            // if I do paint component, everything stays on the canvas, but it flicks with every click
            paintComponent(getGraphics());

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
            //repaint();
            paintComponent(getGraphics());
        }

        @Override
        public void mouseMoved(MouseEvent e) {;}
    }
}
