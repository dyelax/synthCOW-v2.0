import com.leapmotion.leap.Controller;
import java.applet.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.IOException;

public class MainApplet extends Applet{
    private Point2D.Double leftHand, rightHand;

    private boolean handsOnScreen, volumeOn;

    private int circleRadius;

     public void init(){
         setSize(1200, 840);

         leftHand = new Point2D.Double(10, 10);
         rightHand = new Point2D.Double(200, 200);

         circleRadius = 100;

         //setup leapmotion
         // Create a sample listener and controller
         SynthListener listener = new SynthListener();
         Controller controller = new Controller();

         // Have the sample listener receive events from the controller
         controller.addListener(listener);

         // Keep this process running until Enter is pressed
//         System.out.println("Press Enter to quit...");
//         try {
//             System.in.read();
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//         // Remove the sample listener when done
//         controller.removeListener(listener);
     }

    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D)g;

        //draw background
        setBackground(Color.blue);

        if(handsOnScreen) {
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(3));

            g2.drawOval((int) leftHand.getX(), (int) leftHand.getY(), circleRadius, circleRadius);

            if (!volumeOn){
                g2.setColor(new Color(1f, 1f, 1f, 0.4f));
            }
            g2.drawOval((int) rightHand.getX(), (int) rightHand.getY(), circleRadius, circleRadius);

            g2.setColor(new Color(1f, 1f, 1f, 0.5f));

            g2.fillOval((int) leftHand.getX(), (int) leftHand.getY(), circleRadius, circleRadius);

            if (!volumeOn){
                g2.setColor(new Color(1f, 1f, 1f, 0.2f));
            }
            g2.fillOval((int) rightHand.getX(), (int) rightHand.getY(), circleRadius, circleRadius);
        }
    }

    public void setLeftHandPos(double x, double y){
        System.out.println("L: (" + x + ", " + y + ")");

        double xPos = 2*x + 240 + 40;//x from leap goes -120 to 120, plus 20px buffer on side @2x
        if (xPos < 40){
            xPos = 20;
        }else if (xPos > 280){
            xPos = 280;
        }

        double yPos = 200 + (600 - y);//invert y coords from leap and give 100px margin at top
        if (yPos > 800){
            yPos = 800;
        }else if (yPos < 200){
            yPos = 200;
        }

        leftHand.setLocation(xPos, yPos);
    }
    public void setRightHandPos(double x, double y){
        System.out.println("R: ("+x+", "+y+")");

        double xPos = 2*x + 240 + 40;//x from leap goes -120 to 120, plus 20px buffer on side @2x
        if (xPos < 40){
            xPos = 20;
        }else if (xPos > 280){
            xPos = 280;
        }

        double yPos = 200 + (600 - y);//invert y coords from leap and give 100px margin at top
        if (yPos > 800){
            yPos = 800;
        }else if (yPos < 200){
            yPos = 200;
        }

        rightHand.setLocation(xPos, yPos);
    }

    public void setHandsOnScreen(boolean onScreen){
//        System.out.println("onScreen: "+ onScreen ? 1 : 0);
        handsOnScreen = onScreen;
    }

    public void setVolumeOn(boolean on){
        volumeOn = on;
    }

    ///If next == true, switch to the next instrument, else switch to the previous instrument
    public void changeInstrument(boolean next){

    }
}