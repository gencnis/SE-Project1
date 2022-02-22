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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serial;
import java.util.ArrayList;


public class Vid extends JFrame{
    @Serial
    private static final long serialVersionUID = 1L;


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


        restoreButton = new CustomButton("Restore Last", 120, 30, Color.DARK_GRAY, Color.BLACK);
        restoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.restoreLast();
            }
        });

        importButton = new CustomButton("Import Image", 120, 30, Color.DARK_GRAY, Color.BLACK);
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


        textDraw = new CustomButton("Write Text", 120, 30, Color.DARK_GRAY, Color.BLACK);
        textDraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(null, "ENTER WHAT YOU WANT TO WRITE");
                Integer xCoo = Integer.parseInt(JOptionPane.showInputDialog(null, "ENTER X COORDINATE"));
                Integer yCoo = Integer.parseInt(JOptionPane.showInputDialog(null, "ENTER y COORDINATE"));
                canvas.text(input, xCoo, yCoo);
            }
        });

        lineDraw = new CustomButton("draw a Line", 120, 30, Color.DARK_GRAY, Color.BLACK);
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
        // instantiate your button
        saveButton = new CustomButton("Save As JPEG", 120, 30, Color.DARK_GRAY, Color.BLACK);
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

        // instantiate your button
        saveButtonPNG = new CustomButton("Save As PNG", 120, 30, Color.DARK_GRAY, Color.BLACK);
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
        mainContainer.add(eastPanel, BorderLayout.EAST);

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

