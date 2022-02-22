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