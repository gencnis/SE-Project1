/***
This class is made so we can put all of the shapes in it, and then redraw them. This way we save all of the lines in it and then redraw them.
If you'd want to add new shapes, make sure to add them to the ArrayList of all shapes in Vid.java
that way they get called and cleared.

*/



import java.awt.*;
import java.awt.geom.AffineTransform;

public class AllShapes {
    Canvas component;
    Shape shape;

    public AllShapes(Canvas dp, Shape shape) {
        component = dp;
        this.shape = shape;
    }

    public void draw() {
        Graphics2D g2 = (Graphics2D)component.getGraphics();
        g2.setPaint(Color.black);
        g2.draw(shape);
        g2.dispose();
    }

    public void draw(Graphics2D g2) {
        // Offset so you can see the difference.
        AffineTransform at = AffineTransform.getTranslateInstance(20,20);
        g2.draw(at.createTransformedShape(shape));
    }
}
