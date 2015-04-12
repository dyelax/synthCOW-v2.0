import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.GestureList;
import com.leapmotion.leap.Listener;
class  SynthListener extends Listener {

	private int pitch;
	private double vol;
	private GestureHandler gh;
	private double leftX,leftY,rightX,rightY;
	private boolean onScreen, closedHand;
	private MainApplet m;


    public void onConnect(Controller controller) {
        System.out.println("Connected");
        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
        controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
        controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
        gh = new GestureHandler();
		m = new MainApplet();
    }

    public void onFrame(Controller controller) {
        Frame frame = controller.frame();
        GestureList gList = frame.gestures();
        for(Gesture g: gList){
        	switch(g.type()){
        		case TYPE_SCREEN_TAP:
        			if(frame.hands().count() == 2){
        				System.out.println("screentap");
        				//loop
        			}
        			break;
        		case TYPE_SWIPE:
        			if(frame.hands().count() == 1){
        				System.out.println("heyo, swipe right");
        				gh.changeInstrument(true);
        				System.out.println("changed inst");
        			}
        			break;
        		case TYPE_KEY_TAP:
        			if(frame.hands().count() == 2){
        				System.out.println("key tap");
        				//short vol burst

        			}
                    break;
        		default:
                    //for unrecognized gestures, do nothing
                    break;

        	}
        }
        if(frame.hands().count() == 2){
			onScreen = true;

			rightX = frame.hands().rightmost().fingers().frontmost().tipPosition().getX();
			rightY = frame.hands().rightmost().fingers().frontmost().tipPosition().getY();
			leftX = frame.hands().leftmost().fingers().frontmost().tipPosition().getX();
			leftY = frame.hands().leftmost().fingers().frontmost().tipPosition().getY();

			closedHand = frame.hands().rightmost().grabStrength() > .6;

			if(closedHand)
				vol = 0;

			else
				vol = leftY;

	        Finger f = frame.hands().leftmost().fingers().frontmost();

	        int id = f.id();
	       // float noteY = frame.hands().leftmost().finger(id).tipPosition().getY();
	        int i = (int)(leftY/ 60);
	        if(pitch != i){
	        	pitch = i;
	        	//System.out.println(leftY + "     " + i);
	        	gh.changePitch(pitch);
	        }
        }
        else{
			onScreen = false;
        	pitch = 0;
        	vol = 0;
        }

		gh.changeVolume(vol);
		m.setHandsOnScreen(onScreen);
		m.setLeftHandPos(leftX,leftY);
		m.setRightHandPos(rightX,rightX);

    }

//	public double getleftX(){return leftX;}
//	public double getleftY(){return leftY;}
//	public double getrightX(){return rightX;}
//	public double getrightY(){return rightY;}
//
//    public int getPitch(){ return pitch;}
}
