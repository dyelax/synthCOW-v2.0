import java.io.IOException;
import com.leapmotion.leap.*;

public class Sample {
	public static void main(String[] args) {
		Controller controller = new Controller();
		//Alex was here
		//again
		
        // Keep this process running until Enter is pressed
        System.out.println("Press Enter to quit...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
}
