import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionListener;

public class Layout {
 
    // Initialized the buttons
    JButton clearButton;
    JButton redButton;
    JButton greenButton;
    JButton blueButton;
    JButton blackButton;
    JButton orangeButton;
    JButton lightGrayButton;
    JButton yellowButton;
    JButton magentaButton;
    JButton cyanButton;
    JButton darkGrayButton;
    // Class Drawing area
    DrawingArea drawingArea;

    // assigned the action listener to the buttons
    ActionListener actionListener = event -> {
        // clear button
        if (event.getSource() == clearButton) {
        drawingArea.clear();
        } 
        // red button
        else if (event.getSource() == redButton) {
        drawingArea.red();
        } 
        // green button
        else if (event.getSource() == greenButton) {
        drawingArea.green();
        } 
        // blue button
        else if (event.getSource() == blueButton) {
        drawingArea.blue();
        } 
        // black button
        else if (event.getSource() == blackButton) {
        drawingArea.black();
        } 
        // orange button
        else if (event.getSource() == orangeButton) {
        drawingArea.orange();
        } 
        // light gray button
        else if (event.getSource() == lightGrayButton) {
        drawingArea.lightGray();
        } 
        // yellow button
        else if (event.getSource() == yellowButton) {
        drawingArea.yellow();
        } 
        // magenta button
        else if (event.getSource() == magentaButton) {
        drawingArea.magenta();
        } 
        // cyan button
        else if (event.getSource() == cyanButton) {
        drawingArea.cyan();
        } 
        // dark gray button
        else if (event.getSource() == darkGrayButton) {
        drawingArea.darkGray();
        }
    };

    public void show() {

        // this is to create the main frame
        JFrame frame = new JFrame("Paint Application");
        Container content = frame.getContentPane();

        // This is to set layout on content pane
        content.setLayout(new BorderLayout());

        // Creating the drawing area
        drawingArea = new DrawingArea();

        // Adding the drawing area to layout
        content.add(drawingArea, BorderLayout.EAST);

        // This is to add the buttons on plane
        JPanel buttons = new JPanel();
        buttons.setBackground(Color.LIGHT_GRAY);

        clearButton = new JButton("Clear");
        clearButton.addActionListener(actionListener);

        redButton = new JButton("Red");
        redButton.addActionListener(actionListener);

        greenButton = new JButton("Green");
        greenButton.addActionListener(actionListener);

        blueButton = new JButton("Blue");
        greenButton.addActionListener(actionListener);

        blackButton = new JButton("Black");
        blackButton.addActionListener(actionListener);

        orangeButton = new JButton("Orange");
        orangeButton.addActionListener(actionListener);

        lightGrayButton = new JButton("Light Gray");
        lightGrayButton.addActionListener(actionListener);

        yellowButton = new JButton("Yellow");
        yellowButton.addActionListener(actionListener);

        magentaButton = new JButton("Magenta");
        magentaButton.addActionListener(actionListener);

        cyanButton = new JButton("Cyan");
        cyanButton.addActionListener(actionListener);

        darkGrayButton = new JButton("Dark Gray");
        darkGrayButton.addActionListener(actionListener);

        // Adding buttons to the panel
        buttons.add(redButton);
        buttons.add(greenButton);
        buttons.add(blueButton);
        buttons.add(blackButton);
        buttons.add(orangeButton);
        buttons.add(lightGrayButton);
        buttons.add(yellowButton);
        buttons.add(magentaButton);
        buttons.add(cyanButton);
        buttons.add(darkGrayButton);
        buttons.add(clearButton);

        // Adding to the content plane
        content.add(buttons, BorderLayout.WEST);

        // Setting the frame size
        frame.setSize(1000, 800);

        // This enables to close the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // This is to show the results
        frame.setVisible(true);
    }

    // Main to run
    public static void main(String[] args) {
        new Layout().show();
    }
}
