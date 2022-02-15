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
    // CustomButton printButton;
    // CustomButton shareButton;

    Vid(int w, int h, String title) {
        super(title);
        this.setSize(w, h);
        this.setLocation(400, 200);
        Container mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout(5, 5));
        this.getRootPane().setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));

        canvas = new Canvas();

        sliderR = new CustomSlider(0, 255, 0, 51, 10, false);
        sliderG = new CustomSlider(0, 255, 0, 51, 10, false);
        sliderB = new CustomSlider(0, 255, 0, 51, 10, false);

        sliderPenSize = new CustomSlider(0, 50, 0, 10, 20, false);


        saveButton = new CustomButton("Save", 120, 30, Color.DARK_GRAY, Color.BLACK);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BufferedImage img = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_RGB);
                try {
                    ImageIO.write(img, "png", new File("myFile.png"));
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        resetButton = new CustomButton("Clear", 120, 30, Color.red, Color.BLACK);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("whatever");
                canvas.resetArea();
            }
        });

        R = sliderR.getValue();
        G = sliderG.getValue();
        B = sliderB.getValue();

        previewPenColor = new JPanel();
        previewPenColor.setBackground(new Color(R, G, B));

        JLabel labelR = new JLabel("R = 0");
        JLabel labelG = new JLabel("G = 0");
        JLabel labelB = new JLabel("B = 0");
        JLabel labelSize = new JLabel("Point radius = 5");

        JLabel rgbLabel = new JLabel("RGB");
        JLabel rgbPreviewLabel = new JLabel("COLOR PREVIOUS");
        JLabel penSizeLAbel = new JLabel("PEN SIZE");

        sliderPenSize.addChangeListener((new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int size = sliderPenSize.getValue();
                canvas.setPointRadius(size);
                labelSize.setText("Point radius = " + size);
            }
        }));

        sliderR.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                R = sliderR.getValue();
                canvas.setPenColor(new Color(R, G, B));
                previewPenColor.setBackground(new Color(R, G, B));
                labelR.setText("R = " + R);
            }
        });

        sliderG.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                G = sliderG.getValue();
                canvas.setPenColor(new Color(R, G, B));
                previewPenColor.setBackground(new Color(R, G, B));
                labelG.setText("G = " + G);
            }
        });

        sliderB.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                B = sliderB.getValue();
                canvas.setPenColor(new Color(R, G, B));
                previewPenColor.setBackground(new Color(R, G, B));
                labelB.setText("B = " + B);
            }
        });

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(5));
        topPanel.add(saveButton);
        topPanel.add(resetButton);
        mainContainer.add(topPanel, BorderLayout.NORTH);


        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new FlowLayout());

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(12, 1, 30, 30));

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

        eastPanel.add(gridPanel);
        mainContainer.add(canvas);
        mainContainer.add(eastPanel, BorderLayout.EAST);

    }

    public static void main(String[] args){

        Vid window = new Vid(880, 640, "Sparkles project idk");
        window.setVisible(true);






        }

    }

