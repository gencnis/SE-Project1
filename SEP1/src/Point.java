import java.awt.*;

public class Point {
    private int pointX, pointY, pointR;
    Color color;


    public Point(int pointX, int pointY, int pointR, Color color){
        // each point would have its color
        this.color = color;
        // its Radius
        this.pointR = pointR;
        // its X coordinate in the grid
        this.pointX = pointX;
        // its Y coordinate in the grid
        this.pointY = pointY;
    }

    public int getPointX() {
        return pointX;
    }

    public int getPointY() {
        return pointY;
    }

    public int getPointR() {
        return pointR;
    }

    public void drawPoint(Graphics graphic){
        graphic.setColor(color);
        //graphic.fillRect(pointX, pointY, pointR, pointR);
        graphic.fillOval(pointX, pointY, pointR, pointR);
    }
}
