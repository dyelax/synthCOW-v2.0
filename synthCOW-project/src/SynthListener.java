import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.GestureList;
import com.leapmotion.leap.Listener;
class  SynthListener extends Listener {

	private int pitch, vol;
	private GestureHandler gh;
	
    public void onConnect(Controller controller) {
        System.out.println("Connected");
        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
        controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
        controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
        gh = new GestureHandler();
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
	        boolean closedHand = frame.hands().rightmost().grabStrength() > .5;
	        Finger f = frame.hands().leftmost().fingers().frontmost();
	        int id = f.id();
	        float noteY = frame.hands().leftmost().finger(id).tipPosition().getY();
	        int i = (int)(noteY/ 60);
	        if(pitch != i){
	        	pitch = i;
	        	//System.out.println(noteY + "     " + i);
	        	gh.changePitch(pitch);
	        }
        }
        else{
        	pitch = 0;
        	vol = 0;
        }
       
    }
    
//    public int getPitch(){ return pitch;}
}