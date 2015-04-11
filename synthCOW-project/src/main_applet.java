import java.applet.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class main_applet extends Applet{
    Point2D.Double leftHand;
    Point2D.Double rightHand;
     public void init(){
         setSize(1200, 840);

         leftHand = new Point2D.Double(10, 10);
         rightHand = new Point2D.Double(200, 200);
     }

    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D)g;

        setBackground(Color.blue);

        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));

        g2.drawOval((int) leftHand.getX(), (int) leftHand.getY(), 100, 100);
        g2.drawOval((int)leftHand.getX(), (int)leftHand.getY(), 100, 100);

        g2.setColor(new Color(1f, 1f, 1f, 0.5f));

        g2.fillOval((int)leftHand.getX(), (int)leftHand.getY(), 100, 100);
        g2.fillOval((int)leftHand.getX(), (int)leftHand.getY(), 100, 100);
    }
}