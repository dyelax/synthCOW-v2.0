import com.leapmotion.leap.*;
import java.applet.*;
import java.awt.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

public class MainApplet extends Applet implements ActionListener{
    private SynthListener listener;
    private Controller controller;

//    private Point2D.Double leftHand, rightHand;
    private double leftX, leftY, rightX, rightY;

    private boolean onScreen, closedHand;

    private int circleDiameter;

    private int instrumentNum;

    private long lastSwipeTimeMillis;

     public void init(){
         setSize(1080, 840);

         Timer t = new Timer(100, this);
         t.start();

//         leftHand = new Point2D.Double(10, 10);
//         rightHand = new Point2D.Double(200, 200);

         leftX = 10; leftY = 10;
         rightX = 200; rightY = 200;

         circleDiameter = 100;

         instrumentNum = 0;

         lastSwipeTimeMillis = 0;

         //setup leapmotion
         // Create a sample listener and controller
         listener = new SynthListener();
         controller = new Controller();

         // Have the sample listener receive events from the controller
         controller.addListener(listener);
     }

    public void actionPerformed(ActionEvent e) {
        com.leapmotion.leap.Frame frame = controller.frame();

        if(frame.hands().count() == 2){
            onScreen = true;

            double rx = frame.hands().rightmost().fingers().frontmost().tipPosition().getX();
            double ry = frame.hands().rightmost().fingers().frontmost().tipPosition().getY();
            double lx = frame.hands().leftmost().fingers().frontmost().tipPosition().getX();
            double ly = frame.hands().leftmost().fingers().frontmost().tipPosition().getY();

            double xPos = 2*rx + 500 + 40;//x from leap goes -250 to 250, plus 20px buffer on side @2x
            if (xPos < 40){
                xPos = 40;
            }else if (xPos > 1040-circleDiameter){
                xPos = 1040-circleDiameter;
            }

            double yPos = 200 + (600 - 2*ry + 60);//invert y coords from leap and give 100px margin at top @2x
            if (yPos > 800-circleDiameter){
                yPos = 800-circleDiameter;
            }else if (yPos < 200){
                yPos = 200;
            }

            rightX = xPos;
            rightY = yPos;

            xPos = 2*lx + 500 + 40;//x from leap goes -250 to 250, plus 20px buffer on side @2x
            if (xPos < 40){
                xPos = 40;
            }else if (xPos > 1040-circleDiameter){
                xPos = 1040-circleDiameter;
            }

            yPos = 200 + (600 - 2*ly + 60);//invert y coords from leap and give 100px margin at top @2x
            if (yPos > 800-circleDiameter){
                yPos = 800-circleDiameter;
            }else if (yPos < 200) {
                yPos = 200;
            }

            leftX = xPos;
            leftY = yPos;

            closedHand = frame.hands().rightmost().grabStrength() > .6;
        }
        else{
            onScreen = false;
        }

        repaint();
    }
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D)g;

        //
        //draw background
        //
        double pitch = (1000-leftY)/600;
        double volume = (1000-rightY)*0.5/600;

        Color bgColor = Color.getHSBColor((float)pitch, 1f, (float)volume);
        setBackground(bgColor);

        //draw instrument image/handle swipes
        long curTime = System.currentTimeMillis();
        if (curTime - lastSwipeTimeMillis >= 1500){
            instrumentNum += listener.getSwipeNum();
            instrumentNum = instrumentNum % 5;
            lastSwipeTimeMillis = curTime;
        }

        Image instrumentImage;
        switch (instrumentNum){
            case 0:
                instrumentImage = getImage(getCodeBase(), "bass.png");
                break;
            case 1:
                instrumentImage = getImage(getCodeBase(), "keyboard.png");
                break;
            case 2:
                instrumentImage = getImage(getCodeBase(), "strings.png");
                break;
            case 3:
                instrumentImage = getImage(getCodeBase(), "synth.png");
                break;
            case 4:
                instrumentImage = getImage(getCodeBase(), "horns.png");
                break;
            default:
                instrumentImage = getImage(getCodeBase(), "keyboard.png");
                break;
        }

        g2.drawImage(instrumentImage, 200, 200, this);

        g2.setColor(Color.white);
        g2.drawString(""+instrumentNum, 10, 20);


        //note lines

        Rectangle2D line1 = new Rectangle2D.Double(40, 200, 1000, 120);
        Rectangle2D line3 = new Rectangle2D.Double(40, 440, 1000, 120);
        Rectangle2D line5 = new Rectangle2D.Double(40, 680, 1000, 120);

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        float[] fractions = {0f, 0.8f};
        Color[] colors = {new Color(1f, 1f, 1f, 0.4f), new Color(1f, 1f, 1f, 0f)};
        LinearGradientPaint paint = new LinearGradientPaint(40, 0, 1000, 0, fractions, colors);
        g2.setPaint(paint);

        g2.fill(line1);
        g2.fill(line3);
        g2.fill(line5);

//        g2.drawRect(40, 200, 1000, 120);
//        g2.drawRect(40, 440, 1000, 120);
//        g2.drawRect(40, 680, 1000, 120);
//        g2.fillRect(40, 200, 1000, 120);
//        g2.fillRect(40, 440, 1000, 120);
//        g2.fillRect(40, 680, 1000, 120);

        //brackets
        g2.setStroke(new BasicStroke(2));

        g2.drawLine(40, 200, 40, 800);
        g2.drawLine(40, 200, 48, 200);
        g2.drawLine(40, 800, 48, 800);

        g2.drawLine(1040, 200, 1040, 800);
        g2.drawLine(1032, 200, 1040, 200);
        g2.drawLine(1032, 800, 1040, 800);

//        System.out.println("REPAINT L: (" + leftX + ", " + leftY + ")");
//        System.out.println("REPAINT R: ("+rightX+", "+rightY+")");

        //draw hand-circles
        if(onScreen) {
            g2.setStroke(new BasicStroke(3));

            g2.drawOval((int) leftX, (int) leftY, circleDiameter, circleDiameter);

            if (closedHand){
                g2.setColor(new Color(1f, 1f, 1f, 0.4f));
            }
            g2.drawOval((int) rightX, (int) rightY, circleDiameter, circleDiameter);

            g2.setColor(new Color(1f, 1f, 1f, 0.5f));

            g2.fillOval((int) leftX, (int) leftY, circleDiameter, circleDiameter);

            if (closedHand){
                g2.setColor(new Color(1f, 1f, 1f, 0.2f));
            }
            g2.fillOval((int) rightX, (int) rightY, circleDiameter, circleDiameter);
        }
    }

    private java.awt.Image dbImage;
    private Graphics dbg;
    public void update (Graphics g){
        if (dbImage == null) {
            dbImage = createImage (this.getSize().width, this.getSize().height);
            dbg = dbImage.getGraphics ();
        }
        dbg.setColor (getBackground ());
        dbg.fillRect (0, 0, this.getSize().width, this.getSize().height);
        dbg.setColor (getForeground());
        paint (dbg);
        g.drawImage (dbImage, 0, 0, this);
    }
}