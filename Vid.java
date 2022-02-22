/***
 * This is where the major of the logic is taking place
 *
 * @Author : Fehmi Neffati
 *
 * Nisa's Resources:
 *      https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics2D.html
 *      https://docs.oracle.com/javase/8/docs/api/java/awt/Component.html#addMouseMotionListener-java.awt.event.MouseMotionListener-
 *      https://docs.oracle.com/javase/8/docs/api/java/awt/Component.html#addMouseListener-java.awt.event.MouseListener-
 *      https://docs.oracle.com/javase/8/docs/api/java/awt/Component.html#repaint-- 
 *      http://underpop.online.fr/j/java/help/colors-graphics-programming.html.gz 
 *      https://zetcode.com/javaswing/painting/ 
 *      https://stackoverflow.com/questions/12052235/draw-ellipse-using-center-point-not-upper-left-corner
 *      https://stackoverflow.com/questions/35733021/java-draw-rectangles-on-mouse-click 
 *      https://www.w3schools.com/java/java_lambda.asp 
 *      https://docs.oracle.com/javase/7/docs/api/java/awt/Rectangle.html
 * 
 *      Also got an idea from Philip Gray about the algorithm for the shape implementation.
 */


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Vid extends JFrame{

    public Canvas canvas;
    private JPanel previewPenColor;
    private CustomSlider sliderPenSize;
    private CustomSlider sliderR;
    private CustomSlider sliderG;
    private CustomSlider sliderB;

    int R;
    int G;
    int B;

    // just a tester, added some other potential buttons we could do
    CustomButton saveButton;
    CustomButton resetButton;
    CustomButton saveButtonPNG;
    CustomButton lineDraw;
    CustomButton textDraw;
    CustomButton importButton;
    CustomButton restoreButton;
    CustomButton redButton;
    CustomButton greenButton;
    CustomButton blueButton;
    CustomButton blackButton;
    CustomButton orangeButton;
    CustomButton lightGrayButton;
    CustomButton yellowButton;
    CustomButton magentaButton;
    CustomButton cyanButton;
    CustomButton darkGrayButton;
    CustomButton magicWandButton;
    CustomButton drawRecButton;
    CustomButton drawCirButton;
    CustomButton penButton;


    static boolean isPressed = false;


    Canvas getCanvas(){
        return canvas;
    }
    // CustomButton shareButton;

    /***
     *
     * @param w width of the screen
     * @param h height of the screen
     * @param title what will be written upton
     */
    Vid(int w, int h, String title) {
        super(title);
        this.setSize(w, h);
        this.setLocation(400, 200);
        Container mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout(5, 5));
        this.getRootPane().setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));

        // making a new canvas for us to draw on
        canvas = new Canvas();


        // Red slider
        sliderR = new CustomSlider(0, 255, 0, 51, 10, false);
        // Green slider
        sliderG = new CustomSlider(0, 255, 0, 51, 10, false);
        // blue slider
        sliderB = new CustomSlider(0, 255, 0, 51, 10, false);

        //slider for the pren size
        sliderPenSize = new CustomSlider(0, 50, 0, 10, 20, false);


        restoreButton = new CustomButton("Restore Last", 150, 30, Color.LIGHT_GRAY, Color.BLACK);
        restoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.restoreLast();
            }
        });

        importButton = new CustomButton("Import Image", 150, 30, Color.LIGHT_GRAY, Color.BLACK);
        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = "";
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(canvas);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                    path = selectedFile.getAbsolutePath();
                }
                BufferedImage myPicture = null;
                try {
                    myPicture = ImageIO.read(new File(path));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                JLabel picLabel = new JLabel(new ImageIcon(myPicture));
                picLabel.setOpaque(true);
                add(picLabel);
            }
        });


        textDraw = new CustomButton("Write Text", 150, 30, Color.LIGHT_GRAY, Color.BLACK);
        textDraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(null, "ENTER WHAT YOU WANT TO WRITE");
                Integer xCoo = Integer.parseInt(JOptionPane.showInputDialog(null, "ENTER X COORDINATE"));
                Integer yCoo = Integer.parseInt(JOptionPane.showInputDialog(null, "ENTER y COORDINATE"));
                canvas.text(input, xCoo, yCoo);
            }
        });

        lineDraw = new CustomButton("Draw A Line", 120, 30, Color.LIGHT_GRAY, Color.BLACK);
        lineDraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.line();
            }
        });



        /**
         *
         * This is for the save button
         * this whole thing is a template on how to create a button so feel free to duplicate it and follow along
         * to make as many buttons as you'd like
         *
         * You will see me duplicating this same function 3 times to try and save it under multiple formats
         */
        // instantiate the button
        saveButton = new CustomButton("Save As JPEG", 150, 30, Color.LIGHT_GRAY, Color.BLACK);
        //here you're adding the action listener (basically the funcionality of the button: what you want it to do.)
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // when the save button is saved, I want it to make a buffered Image
                BufferedImage image = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_RGB);
                // turn that buffered image into a graphics2d object
                Graphics2D g2 = image.createGraphics();
                // and now I want to paint that object with what I have in the canvas
                canvas.paint(g2);
                try {
                    // here we try to save the image under a png file
                    ImageIO.write(image, "jpeg", new File("myArt.jpeg"));
                } catch (IOException o) {
                    o.printStackTrace();
                }
            }
        });

        // instantiate the button
        saveButtonPNG = new CustomButton("Save As PNG", 150, 30, Color.LIGHT_GRAY, Color.BLACK);
        //here you're adding the action listener (basically the functionality of the button: what you want it to do.)
        saveButtonPNG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // when the save button is saved, I want it to make a buffered Image
                BufferedImage image = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_RGB);
                // turn that buffered image into a graphics2d object
                Graphics2D g2 = image.createGraphics();
                // and now I want to paint that object with what I have in the canvas
                canvas.paint(g2);
                try {
                    // here we try to save the image under a png file
                    ImageIO.write(image, "png", new File("myArt.png"));
                } catch (IOException o) {
                    o.printStackTrace();
                }
            }
        });

        // reset button to clean up the canvas and reset it to white
        resetButton = new CustomButton("Clear", 120, 30, Color.RED, Color.BLACK);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.resetArea();
            }
        });

        //NISA
        // instantiate the color red button
        redButton = new CustomButton("", 30, 30, Color.RED , Color.RED);
        redButton.addActionListener(e ->{
            Color color = Color.red;
            canvas.setPenColor(color);
            previewPenColor.setBackground(color);
            
            //puts the value of the color in sliders
            sliderR.setValue(255);
            sliderG.setValue(0);
            sliderB.setValue(0);
        });

        // instantiate the color green button
        greenButton = new CustomButton("", 30, 30, Color.GREEN , Color.GREEN);
        greenButton.addActionListener(e ->{
            Color color = Color.GREEN;
            canvas.setPenColor(color);
            previewPenColor.setBackground(color);
            
            //puts the value of the color in sliders
            sliderR.setValue(0);
            sliderG.setValue(255);
            sliderB.setValue(0);
        });

        // instantiate the color blue button
        blueButton = new CustomButton("", 30, 30, Color.BLUE , Color.BLUE);
        blueButton.addActionListener(e ->{
            Color color = Color.BLUE;
            canvas.setPenColor(color);
            previewPenColor.setBackground(color);
            
            //puts the value of the color in sliders
            sliderR.setValue(0);
            sliderG.setValue(0);
            sliderB.setValue(255);
        });

        // instantiate the color black button
        blackButton = new CustomButton("", 30, 30, Color.BLACK , Color.BLACK);
        blackButton.addActionListener(e ->{
            Color color = Color.BLACK;
            canvas.setPenColor(color);
            previewPenColor.setBackground(color);
            
            //puts the value of the color in sliders
            sliderR.setValue(0);
            sliderG.setValue(0);
            sliderB.setValue(0);
        });

        // instantiate the color orange button
        orangeButton = new CustomButton("", 30, 30, Color.ORANGE , Color.ORANGE);
        orangeButton.addActionListener(e ->{
            Color color = Color.ORANGE;
            canvas.setPenColor(color);
            previewPenColor.setBackground(color);
            
            //puts the value of the color in sliders
            sliderR.setValue(255);
            sliderG.setValue(200);
            sliderB.setValue(0);
        });

        // instantiate the color light gray button
        lightGrayButton = new CustomButton("", 30, 30, Color.LIGHT_GRAY , Color.LIGHT_GRAY);
        lightGrayButton.addActionListener(e ->{
            Color color = Color.LIGHT_GRAY;
            canvas.setPenColor(color);
            previewPenColor.setBackground(color);
            
            //puts the value of the color in sliders
            sliderR.setValue(192);
            sliderG.setValue(192);
            sliderB.setValue(192);
        });

        // instantiate the color yellow button
        yellowButton = new CustomButton("", 30, 30, Color.YELLOW , Color.YELLOW);
        yellowButton.addActionListener(e ->{
            Color color = Color.YELLOW;
            canvas.setPenColor(color);
            previewPenColor.setBackground(color);
            
            //puts the value of the color in sliders
            sliderR.setValue(255);
            sliderG.setValue(255);
            sliderB.setValue(0);
        });
        
        // instantiate the color magenta button
        magentaButton = new CustomButton("", 30, 30, Color.MAGENTA , Color.MAGENTA);
        magentaButton.addActionListener(e ->{
            Color color = Color.MAGENTA;
            canvas.setPenColor(color);
            previewPenColor.setBackground(color);
            
            //puts the value of the color in sliders
            sliderR.setValue(255);
            sliderG.setValue(0);
            sliderB.setValue(255);
        });

        // instantiate the color cyan button
        cyanButton = new CustomButton("", 30, 30, Color.CYAN , Color.CYAN);
        cyanButton.addActionListener(e ->{
            Color color = Color.CYAN;
            canvas.setPenColor(color);
            previewPenColor.setBackground(color);
            
            //puts the value of the color in sliders
            sliderR.setValue(0);
            sliderG.setValue(255);
            sliderB.setValue(255);
        });

        // instantiate the color dark gray button
        darkGrayButton = new CustomButton("", 30, 30, Color.DARK_GRAY , Color.DARK_GRAY);
        // The change listener to the dark gray color slider
        darkGrayButton.addActionListener(e ->{
            Color color = Color.DARK_GRAY;
            // sets it
            canvas.setPenColor(color);
            // displays the color on the preview panel
            previewPenColor.setBackground(color);
            
            //puts the value of the color in sliders
            sliderR.setValue(64);
            sliderG.setValue(64);
            sliderB.setValue(64);
        });

        // This is basically a white color box, but we can use it to do the magic wand
        magicWandButton = new CustomButton("Magic Wand", 110, 30, Color.WHITE , Color.BLACK);
        // The change listener to the color white slider
        magicWandButton.addActionListener(e ->{
            Color color = Color.WHITE;
            // sets it
            canvas.setPenColor(color);
            // displays the color on the preview panel
            previewPenColor.setBackground(color);
            
            //puts the value of the color in sliders
            sliderR.setValue(255);
            sliderG.setValue(255);
            sliderB.setValue(255);
        });

        // instantiate the rectangle shape button
        drawRecButton = new CustomButton("Rectangle", 120, 30, Color.lightGray , Color.BLACK);
        drawRecButton.addActionListener(e ->{
            canvas.changeShape("rectangle");
            canvas.setPenColor(new Color(sliderR.getValue(), sliderG.getValue(), sliderB.getValue()));

        });

        // instantiate the draw circle button
        drawCirButton = new CustomButton("Circle", 120, 30, Color.lightGray , Color.BLACK);
        drawCirButton.addActionListener(e ->{
        canvas.changeShape("circle");
        canvas.setPenColor(new Color(sliderR.getValue(), sliderG.getValue(), sliderB.getValue()));
        });

        // instantiate the pen button
        penButton = new CustomButton("Pen", 60, 20, Color.lightGray , Color.BLACK);
        penButton.addActionListener(e ->{
        canvas.changeShape("pen");
        canvas.setPenColor(new Color(sliderR.getValue(), sliderG.getValue(), sliderB.getValue()));
        });
        //NISA

        // The RGB sliders default values
        R = sliderR.getValue();
        G = sliderG.getValue();
        B = sliderB.getValue();

        // the panel where we preview the color changes
        previewPenColor = new JPanel();
        // gets set up with the initial RGB values
        previewPenColor.setBackground(new Color(R, G, B));


        // These are the label for the sliders (goes on top of them)
        JLabel labelR = new JLabel("R = 0");
        JLabel labelG = new JLabel("G = 0");
        JLabel labelB = new JLabel("B = 0");
        JLabel labelSize = new JLabel("Point radius = 5");

        // Label for the whole left panel
        JLabel rgbLabel = new JLabel("RGB");
        // label for the preview panel
        JLabel rgbPreviewLabel = new JLabel("COLOR:");
        // cursor size slider title
        JLabel penSizeLAbel = new JLabel("PEN SIZE");

        // The change listener to the pen size slider
        sliderPenSize.addChangeListener((new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // gets the new value once you slide it
                int size = sliderPenSize.getValue();
                // sets it
                canvas.setPointRadius(size);
                // writes it out on the screen
                labelSize.setText("Point radius = " + size);
            }
        }));

        // The change listener to the Red color slider
        sliderR.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // gets the new value once you slide it
                R = sliderR.getValue();
                // sets it
                canvas.setPenColor(new Color(R, G, B));
                // displays the color on the preview panel
                previewPenColor.setBackground(new Color(R, G, B));
                // writes it out on the screen
                labelR.setText("R = " + R);
            }
        });

        // The change listener to the Green color slider
        sliderG.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // gets the new value once you slide it
                G = sliderG.getValue();
                // sets it
                canvas.setPenColor(new Color(R, G, B));
                // displays the color on the preview panel
                previewPenColor.setBackground(new Color(R, G, B));
                // writes it out on the screen
                labelG.setText("G = " + G);
            }
        });

        // The change listener to the Blue color slider

        sliderB.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // gets the new value once you slide it

                B = sliderB.getValue();
                // sets it

                canvas.setPenColor(new Color(R, G, B));
                // displays the color on the preview panel

                previewPenColor.setBackground(new Color(R, G, B));
                // writes it out on the screen

                labelB.setText("B = " + B);
            }
        });



        // creates a new  top panel
        JPanel topPanel = new JPanel();
        // aligns it
        topPanel.setLayout(new FlowLayout(5));

        //NISA
        // Top panel default color buttons
        topPanel.add(redButton); //add red color button
        topPanel.add(greenButton); // add green color button
        topPanel.add(blueButton); //add blue color button
        topPanel.add(blackButton); //add black color button
        topPanel.add(orangeButton); //add orange color button
        topPanel.add(lightGrayButton); //light gray color button
        topPanel.add(yellowButton); //add yellow color button
        topPanel.add(magentaButton); //add magenta color button
        topPanel.add(cyanButton); //add cyan color button
        topPanel.add(darkGrayButton); //add dark gray color button

        topPanel.add(magicWandButton); //add magic wand button
        //NISA

        // adding the JPG save button
        topPanel.add(saveButton);
        // adding the PNG save button
        topPanel.add(saveButtonPNG);
        // adding the reset the canvas button
        topPanel.add(resetButton);
        // adding the  line drawing button
        topPanel.add(lineDraw);
        //
        topPanel.add(textDraw);
        //
        topPanel.add(importButton);
        //
        topPanel.add(restoreButton);
        // adds the top panel in the north of the jpanel
        // could change to east or west depending on what you want
        mainContainer.add(topPanel, BorderLayout.NORTH);

        //NISA
        JPanel shapesPanel = new JPanel();
        shapesPanel.setLayout(new FlowLayout());
        shapesPanel.add(drawRecButton);
        shapesPanel.add(drawCirButton);
        //NISA


        // this is the east panel that contains the color sliders
        JPanel eastPanel = new JPanel();
        // sets it up
        eastPanel.setLayout(new FlowLayout());

        //Think of the east panel as an amazon box and the grid panel as the product box that you bought
        // it's a lot of boxes within boxes, but you wouldn't want the items to be floating around that amazon box when
        // shipped

        // makes a grid
        JPanel gridPanel = new JPanel();
        // sets the layout and the distance between the cols and rows
        // you could set it up where you have multiple columns if you want to have all of the colors set up as a box kinda
        gridPanel.setLayout(new GridLayout(15, 1, 20, 1));

        // Here i add all of items to that grid panel on the right side of the screen
        gridPanel.add(penButton); // adds pen button to the side panel
        gridPanel.add(shapesPanel); // adds shapes button panel to the side panel
        gridPanel.add(magicWandButton); // adds magic wand button to the side panel
        gridPanel.add(rgbLabel);
        gridPanel.add(labelR);
        gridPanel.add(sliderR);

        gridPanel.add(labelG);
        gridPanel.add(sliderG);

        gridPanel.add(labelB);
        gridPanel.add(sliderB);

        gridPanel.add(rgbPreviewLabel);
        gridPanel.add(previewPenColor);

        gridPanel.add(penSizeLAbel);
        gridPanel.add(sliderPenSize);
        gridPanel.add(labelSize);


        //Here I add the grid panel to the east panel
        eastPanel.add(gridPanel);

        // here I add the canvas to the main container
        mainContainer.add(canvas);
        // and the east panel to the main container
        // this could be changed to west or south depending on preferenece
        mainContainer.add(eastPanel, BorderLayout.WEST);

    }

    public static void main(String[] args){

        Vid window = new Vid(1000, 600, "Sparkles project");
        window.setVisible(true);
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);

                BufferedImage reloader = new BufferedImage(window.getCanvas().getWidth(), window.getCanvas().getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D gg = reloader.createGraphics();
                // and now I want to paint that object with what I have in the canvas
                window.getCanvas().paint(gg);
                try {
                    // here we try to save the image under a png file
                    ImageIO.write(reloader, "jpeg", new File("default.jpeg"));
                } catch (IOException o) {
                    o.printStackTrace();
                }

            }
        });
    }
}

