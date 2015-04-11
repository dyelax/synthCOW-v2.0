import com.leapmotion.leap.*;
class SampleListener extends Listener {

    public void onConnect(Controller controller) {
        System.out.println("Connected");
        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
    }

    public void onFrame(Controller controller) {
        Frame frame = controller.frame();
        Finger f = frame.hands().leftmost().fingers().frontmost();
        int id = f.id();
        float noteY = frame.hands().leftmost().finger(id).tipPosition().getY();
        int i = (int)(noteY/ 60);
       	System.out.println(noteY + "     " + i);
//        System.out.println("f" + f);
//        System.out.println("id" + id);
//        System.out.println("pos" + notePos);
       //	System.out.println(frame.interactionBox().height());
        
       
    }
}