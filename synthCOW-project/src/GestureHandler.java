
public class GestureHandler {
	private Synth cow = new Synth();
	private int instrument;
	private int numInstruments;
	
	private boolean isSilent;
	
	public GestureHandler(){
		
	}
	
	public void changeInstrument(boolean b){
		//changes instruments; next if true, previous if false
		
		if(b == true){
			instrument = (instrument + 1) % numInstruments;
		}else{
			if(instrument == 0){
				instrument = numInstruments - 1;
			}else{
				instrument--;
			}
		}

		cow.setInstruments(instrument);
	}
	
	public void changePitch(int pitch){
		if(pitch < 0 || pitch > 5){
			System.out.println("Pitch is out of bounds!");
			return;
		}
		if(pitch == 0){
			activateVolume(false);
			return;
		}

		activateVolume(true);

		if(pitch == 1){

			//cow.playNote*
		}else if(pitch == 2){
			//cow.playNote*****(b);
		}else if(pitch == 3){
			
		}else if(pitch == 4){
			
		}else if(pitch == 5){
			
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
		
		cow.setIsPlaying(on);
		
	}
	
	public void loop(){
		//Loop sound from last recording start (either record() or loop()) and begin a new recording
	}
	
	public void record(){
		//Similar to loop() but only used at beginning
	}
	
	

}
