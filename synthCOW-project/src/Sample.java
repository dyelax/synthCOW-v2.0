import java.io.IOException;
import com.leapmotion.leap.*;

<<<<<<< HEAD
class Sample {
    public static void main(String[] args) {
        // Create a sample listener and controller
        SampleListener listener = new SampleListener();
        Controller controller = new Controller();

        // Have the sample listener receive events from the controller
        controller.addListener(listener);

=======
public class Sample {
	public static void main(String[] args) {
		Controller controller = new Controller();
		//Alex was here
		
>>>>>>> parent of 1036e4e... asdkjfl
        // Keep this process running until Enter is pressed
        System.out.println("Press Enter to quit...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Remove the sample listener when done
        controller.removeListener(listener);
    }
}