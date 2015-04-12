import com.leapmotion.leap.*;

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
		leftY = 0;
		leftX = 0;
		rightY = 0;
		rightX = 0;
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
						SwipeGesture swipe = new SwipeGesture(g);
						Vector swipeDir= swipe.direction();
						if(swipeDir.getX() > 0) {
							//gh.changeInstrument(true);
							//m.changeInstrument(true);
						}
						else{
							//gh.changeInstrument(false);
							//m.changeInstrument(false);
						}
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

			if(closedHand) {
				vol = 0;
			}else {
				vol = rightY;
			}

	        Finger f = frame.hands().leftmost().fingers().frontmost();

	        int id = f.id();
	       // float noteY = frame.hands().leftmost().finger(id).tipPosition().getY();
	        int i = (int)(leftY/ 60);
	        if(pitch != i){
	        	pitch = i;
	        	System.out.println(leftY + "     " + i);
	        	gh.changePitch(pitch);
	        }
		}
        else{
			onScreen = false;
        	pitch = 0;
			gh.changePitch(pitch);
        	vol = 0;
			gh.changeVolume(vol);
        }

		gh.changeVolume(vol);
		//System.out.println(frame.hands().leftmost().fingers().frontmost().tipPosition().getX());
		//m.setHandsOnScreen(onScreen);
//		Runnable callApplet1 = new Runnable() {
//			@Override
//			public void run() {
//				try{m.setLeftHandPos(leftX, leftY);}catch(Exception e){e.printStackTrace(); Thread.currentThread().interrupt();}
//			}
//		};
//		Runnable callApplet2 = new Runnable() {
//			@Override
//			public void run() {
//				try{m.setRightHandPos(rightX, rightY);}catch(Exception e){e.printStackTrace(); Thread.currentThread().interrupt();}
//			}
//		};
//		Thread t1 = new Thread(callApplet1);
//		t1.start();
//
//		Thread t2 = new Thread(callApplet1);
//		t2.start();

		//System.out.println("heyL: (" + leftX + "," + leftY + ") R: (" + rightX + "," + rightY + ")  on " + onScreen + " pitch: " + pitch + " closed " + closedHand);
//		m.setRightHandPos(rightX, rightY);
//		m.setLeftHandPos(leftX, leftY);

		//m.setHandPos(leftX, leftY, rightX, rightY);
    }

//	public double getleftX(){return leftX;}
//	public double getleftY(){return leftY;}
//	public double getrightX(){return rightX;}
//	public double getrightY(){return rightY;}
//
//    public int getPitch(){ return pitch;}
}
