
public class GestureHandler {
	//private SynthCOW cow = new SynthCOW;
	//private int instrument;
	
	public GestureHandler(){
		
	}
	
	public void changeInstrument(boolean b){
		if(b == true){
			
		}else{
			
		}
	}
	
	public void changePitch(int pitch){
		if(pitch < 0 || pitch > 4){
			System.out.println("Pitch is out of bounds!");
			return;
		}
		
		if(pitch == 0){
			//cow.playNote*****(a);
		}else if(pitch == 1){
			//cow.playNote*****(b);
		}else if(pitch == 2){
			
		}else if(pitch == 3){
			
		}else if(pitch == 4){
			
		}
	}
	
	public void changeVolume(double volume){
		//calculate volume values to pass to cow; pure math
		
		if(volume < 0.0 || volume > 11){
			System.out.println("Volume value is out of bounds!");
			return;
		}
		
		
	}
	
	public void activateVolume(boolean on){
		//Used to activate sound
	}
	
	public void loop(){
		//Loop sound from last recording start (either record() or loop()) and begin a new recording
	}
	
	public void record(){
		//Similar to loop() but only used at beginning
	}
	
	

}
