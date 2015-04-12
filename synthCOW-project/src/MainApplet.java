import java.applet.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class MainApplet extends Applet{
    private Point2D.Double leftHand, rightHand;

    private boolean handsOnScreen, volumeOn;

     public void init(){
         setSize(1200, 840);

         leftHand = new Point2D.Double(10, 10);
         rightHand = new Point2D.Double(200, 200);
     }

    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D)g;

        setBackground(Color.blue);

        if(handsOnScreen) {
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(3));

            g2.drawOval((int) leftHand.getX(), (int) leftHand.getY(), 100, 100);

            if (!volumeOn){
                g2.setColor(new Color(1f, 1f, 1f, 0.5f));
            }
            g2.drawOval((int) rightHand.getX(), (int) rightHand.getY(), 100, 100);

            g2.setColor(new Color(1f, 1f, 1f, 0.5f));

            g2.fillOval((int) leftHand.getX(), (int) leftHand.getY(), 100, 100);

            if (!volumeOn){
                g2.setColor(new Color(1f, 1f, 1f, 0.25f));
            }
            g2.fillOval((int) rightHand.getX(), (int) rightHand.getY(), 100, 100);
        }
    }

    public void setLeftHandPos(double x, double y){
        leftHand.setLocation(x, y);
    }
    public void setRightHandPos(double x, double y){
        rightHand.setLocation(x, y);
    }

    public void setHandsOnScreen(boolean onScreen){
        handsOnScreen = onScreen;
    }

    public void setVolumeOn(boolean on){
        volumeOn = on;
    }
}