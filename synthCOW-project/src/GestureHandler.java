import javax.sound.midi.Sequencer;

public class GestureHandler {
	private Synth cow = new Synth();
	private int instrument;
	private int numInstruments;
	private boolean clockYes;
	private Sequencer sequencer;
	
	public GestureHandler(){
		cow = new Synth();
		sequencer = null;
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

		cow.setInstrument(instrument);
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

			cow.setPitch(60);
		}else if(pitch == 2){
			cow.setPitch(63);
		}else if(pitch == 3){
			cow.setPitch(65);
		}else if(pitch == 4){
			cow.setPitch(67);
		}else if(pitch == 5){
			cow.setPitch(70);
		}
	}
	
	public void changeVolume(double volume){
		//calculate volume values to pass to cow; pure math

//		if(volume < 0.0 || volume > 300.0){
//			System.out.println("Volume value is out of bounds!");
//			return;
//		}
		if(volume > 500){
			volume = 500;
		}
		cow.setVolume((int)((volume*127)/500));
		
		
	}
	
	public void activateVolume(boolean on){
		//Used to activate sound

		if(clockYes != on || on) {
			cow.setIsPlaying(on);

			if (!cow.getIsPlaying()) {
				cow.stopNote(sequencer);
			} else {
				cow.stopNote(sequencer);
				sequencer = cow.playNote();
			}
			clockYes = on;
		}
		
	}
	
	public void loop(){
		//Loop sound from last recording start (either record() or loop()) and begin a new recording
	}
	
	public void record(){
		//Similar to loop() but only used at beginning
	}
	
	

}
