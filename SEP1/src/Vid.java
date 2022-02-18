/***
 * This is where the major of the logic is taking place
 *
 * Feb 14th: Problems: The saved picture is completely black, also, the main class lacks documentation
 *
 * @Author : Fehmi Neffati
 *
 */




import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serial;


public class Vid extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;


    private Canvas canvas;
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

    // CustomButton printButton;
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

        /**
         *
         * This is for the save button
         * this whole thing is a template on how to create a button so feel free to duplicate it and follow along
         * to make as many buttons as you'd like
         *
         * You will see me duplicating this same function 3 times to try and save it under multiple formats
         */
        // instantiate your button
        saveButton = new CustomButton("Save As JPG", 120, 30, Color.LIGHT_GRAY, Color.BLACK);
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
                    ImageIO.write(image, "jpg", new File("myArt.jpg"));
                } catch (IOException o) {
                    o.printStackTrace();
                }
            }
        });

        // instantiate your button
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
        resetButton = new CustomButton("Clear", 120, 30, new Color(255,97,97), Color.BLACK);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.resetArea();
            }
        });

        //NISA
        // instantiate your button
        redButton = new CustomButton("", 30, 30, Color.RED , Color.RED);
        // The change listener to the Red color slider
        redButton.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                // sets it
                canvas.setPenColor(new Color(255,0,0));
                // displays the color on the preview panel
                previewPenColor.setBackground(new Color(255,0,0));

                sliderR.setValue(255);
                sliderG.setValue(0);
                sliderB.setValue(0);
            }
        });

        greenButton = new CustomButton("GREEN", 30, 30, Color.GREEN, Color.GREEN);
        // The change listener to the Green color slider
        greenButton.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                // sets it
                canvas.setPenColor(new Color(0,255,0));
                // displays the color on the preview panel
                previewPenColor.setBackground(new Color(0,255,0));

                sliderR.setValue(0);
                sliderG.setValue(255);
                sliderB.setValue(0);
            }
        });

        blueButton = new CustomButton("BLUE", 30, 30, Color.BLUE, Color.BLUE);
        // The change listener to the Blue color slider
        blueButton.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                // sets it
                canvas.setPenColor(new Color(0,0,255));
                // displays the color on the preview panel
                previewPenColor.setBackground(new Color(0,0,255));

                sliderR.setValue(0);
                sliderG.setValue(0);
                sliderB.setValue(255);
            }
        });

        blackButton = new CustomButton("BLACK", 30, 30, Color.BLACK, Color.BLACK);
        // The change listener to the black color slider
        blackButton.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                // sets it
                canvas.setPenColor(new Color(0,0,0));
                // displays the color on the preview panel
                previewPenColor.setBackground(new Color(0,0,0));

                sliderR.setValue(0);
                sliderG.setValue(0);
                sliderB.setValue(0);
            }
        });

        orangeButton = new CustomButton("ORANGE", 30, 30, Color.ORANGE, Color.ORANGE);
        // The change listener to the orange color slider
        orangeButton.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                // sets it
                canvas.setPenColor(new Color(255,200,0));
                // displays the color on the preview panel
                previewPenColor.setBackground(new Color(255,200,0));

                sliderR.setValue(255);
                sliderG.setValue(200);
                sliderB.setValue(0);
            }
        });

        lightGrayButton = new CustomButton("LIGHT GRAY", 30, 30, Color.LIGHT_GRAY, Color.LIGHT_GRAY);
        // The change listener to the light gray color slider
        lightGrayButton.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                // sets it
                canvas.setPenColor(new Color(192,192,192));
                // displays the color on the preview panel
                previewPenColor.setBackground(new Color(192,192,192));

                sliderR.setValue(192);
                sliderG.setValue(192);
                sliderB.setValue(192);
            }
        });

        yellowButton = new CustomButton("YELLOW", 30, 30, Color.YELLOW, Color.YELLOW);
        // The change listener to the yellow color slider
        yellowButton.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                // sets it
                canvas.setPenColor(new Color(255,255,0));
                // displays the color on the preview panel
                previewPenColor.setBackground(new Color(255,255,0));

                sliderR.setValue(255);
                sliderG.setValue(255);
                sliderB.setValue(0);
            }
        });

        magentaButton = new CustomButton("MAGENTA", 30, 30, Color.MAGENTA, Color.MAGENTA);
        // The change listener to the magenta color slider
        magentaButton.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                // sets it
                canvas.setPenColor(new Color(255,0,255));
                // displays the color on the preview panel
                previewPenColor.setBackground(new Color(255,0,255));


                sliderR.setValue(255);
                sliderG.setValue(0);
                sliderB.setValue(255);
            }
        });

        cyanButton = new CustomButton("CYAN", 30, 30, Color.CYAN, Color.CYAN);
        // The change listener to the cyan color slider
        cyanButton.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                // sets it
                canvas.setPenColor(new Color(0,255,255));
                // displays the color on the preview panel
                previewPenColor.setBackground(new Color(0,255,255));

                sliderR.setValue(0);
                sliderG.setValue(255);
                sliderB.setValue(255);
            }
        });

        darkGrayButton = new CustomButton("DARK GRAY", 30, 30, Color.DARK_GRAY, Color.DARK_GRAY);
        // The change listener to the dark gray color slider
        darkGrayButton.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                // sets it
                canvas.setPenColor(new Color(64,64,64));
                // displays the color on the preview panel
                previewPenColor.setBackground(new Color(64,64,64));

                sliderR.setValue(64);
                sliderG.setValue(64);
                sliderB.setValue(64);
            }
        });

        // This is basically a white color box, but we can use it to do the magic wand
        magicWandButton = new CustomButton("MAGIC WAND", 150, 30, Color.WHITE, Color.BLACK);
        // The change listener to the white color slider
        magicWandButton.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                // sets it
                canvas.setPenColor(Color.WHITE);
                // displays the color on the preview panel
                previewPenColor.setBackground(Color.WHITE);

                sliderR.setValue(255);
                sliderG.setValue(255);
                sliderB.setValue(255);
            }
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

        // The change listener to the Green color slider

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
        //NISA TODO

        // Top panel default color buttons
        topPanel.add(redButton);
        topPanel.add(greenButton);
        topPanel.add(blueButton);
        topPanel.add(blackButton);
        topPanel.add(orangeButton);
        topPanel.add(lightGrayButton);
        topPanel.add(yellowButton);
        topPanel.add(magentaButton);
        topPanel.add(cyanButton);
        topPanel.add(darkGrayButton);

        topPanel.add(magicWandButton);

        //NISA TODO
        // adding the JPG save button
        topPanel.add(saveButton);
        // adding the PNG save button
        topPanel.add(saveButtonPNG);
        // adding the reset the canvas button
        topPanel.add(resetButton);
        // adds the top panel in the north of the jpanel

        mainContainer.add(topPanel, BorderLayout.NORTH);


        // this is the east panel that contains the color sliders
        JPanel eastPanel = new JPanel();
        // sets it up
        eastPanel.setLayout(new FlowLayout());

        //Think of the east panel as an amazon box and the grid panel as the product box that you bought
        // it's a lot of boxes within boxes, but you wouldn't want your items to be floating around that amazon box when
        // shipped

        // makes a grid
        JPanel gridPanel = new JPanel();
        // sets the layout and the distance between the cols and rows
        // you could set it up where you have multiple columns if you want to have all of the colors set up as a box kinda
        gridPanel.setLayout(new GridLayout(12, 1, 30, 30));

        // Here i add all of items to that grid panel on the right side of the scree
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

        Vid window = new Vid(880, 640, "Sparkles project");
        window.setVisible(true);
        }

    }

