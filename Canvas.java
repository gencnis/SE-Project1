
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.*;
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

    private String currentShape;
    //
    HashMap<String, Integer[]> textCoordinates;


    // array containing the grid
    private ArrayList<Point> pointList;

    ArrayList<AllShapes> shapes;
    ArrayList<String> allText;

    public ArrayList<Shape> circlesAndRectangles;
    public ArrayList<Color> shapeColors;
    public Color currentColor;

    /**
     * this class instantiates the canvas area and sets the background to white and the brush to black
     */

    Canvas()
    {
        this.addMouseListener(new MouseEventHandler());
        this.addMouseMotionListener(new MouseMotionHandler());

        backColor = new Color(255, 255, 255);
        penColor = new Color(0, 0, 0);
        this.setBackground(backColor);

        pointList = new ArrayList<>();
        pointR = 5;

        this.currentShape="pen";
        this.shapes = new ArrayList<>();
        this.allText = new ArrayList<>();
        this.textCoordinates = new HashMap<>();
        this.circlesAndRectangles = new ArrayList<>();
        this.shapeColors = new ArrayList<>();
        this.currentColor = currentColor;
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
        } //an arraylist for the shapes that can be drawn
        for (Shape circlesAndRectangle: circlesAndRectangles){
            g2.draw(circlesAndRectangle);
        } // colors for the shapes
        for (int i=0; i<circlesAndRectangles.size();i++){
            g2.setColor(shapeColors.get(i));
            g2.fill(circlesAndRectangles.get(i));
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

    /**
     * This function changes the shape according to the pressed button.
     * @param shape gets what shape was tried to be drawn
     */
    public void changeShape(String shape) {
        if(shape.equals("circle")){
            currentShape = "circle";
            currentColor = penColor;

        } else if(shape.equals("rectangle")){
            currentShape = "rectangle";
            currentColor = penColor;
        } else {
            currentShape = "pen";
            currentColor = penColor;
        }
    }

    private class MouseEventHandler implements MouseListener {
        // when the user clicks the mouse
        public void mousePressed(MouseEvent e) {
            if(currentShape.equals("pen")){
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
            // checks if the pressed button is circle button.
            else if (currentShape.equals("circle")){
                // we get the x coordinate
               pointX = (int) (e.getX() - (90 / 2.0));
               // and the y coordinate
               pointY = (int) (e.getY() - (90 / 2.0));
               circlesAndRectangles.add(new Ellipse2D.Double(pointX, pointY, 90, 90));
               shapeColors.add(currentColor);
            } 
            // checks if the pressed button is circle rectangle.
            else if (currentShape.equals("rectangle")){
               pointX = e.getX() - (90 / 2);
               // and the y coordinate
               pointY = e.getY() - (90 / 2);
               circlesAndRectangles.add(new Rectangle(pointX, pointY, 90, 90));
               shapeColors.add(currentColor);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e){;}
        

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
