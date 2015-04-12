import javax.sound.midi.Sequencer;

public class GestureHandler {
	private Synth cow = new Synth();
	private int instrument;
	private int numInstruments = 5;
	private int prevPitch;
	private Sequencer sequencer;
	private int[] instruments;
	
	public GestureHandler(){
		cow = new Synth();
		sequencer = null;

		instruments = new int[numInstruments];

		instruments[0]=89;
		instruments[1]=1;
		instruments[2]=25;
		instruments[3]=17;
		instruments[4]=81;
		cow.setInstrument(instruments[0]);
		instrument = 0;


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

		cow.setInstrument(instruments[instrument]);
	}
	
	public void changePitch(int pitch){
		if(pitch < 0){
			System.out.println("Pitch is out of bounds!");
			return;
		}
		if(pitch == 0){
			cow.setPitch(0);
			activateVolume(false);
			return;
		}


		if(pitch == 1){

			cow.setPitch(60);
		}else if(pitch == 2){
			cow.setPitch(63);
		}else if(pitch == 3){
			cow.setPitch(65);
		}else if(pitch == 4){
			cow.setPitch(67);
		}else if(pitch >= 5){
			cow.setPitch(70);
		}

		activateVolume(true);

	}
	
	public void changeVolume(double volume){
		//calculate volume values to pass to cow; pure math

//		if(volume < 0.0 || volume > 300.0){
//			System.out.println("Volume value is out of bounds!");
//			return;
//		}
		if(volume > 300){
			volume = 300;
		}
		cow.setVolume((int)((volume*127)/300));
		//cow.setVolume(127);
		
	}
	
	public void activateVolume(boolean on){
		//Used to activate sound

		if(prevPitch != cow.getPitch()) {
			cow.setIsPlaying(on);

			//if(sequencer.isRunning()) {
				cow.stopNote(sequencer);
			//-}

			if (cow.getIsPlaying()) {
				sequencer = cow.playNote();
			}

			prevPitch = cow.getPitch();
		}
		
	}
	
	public void loop(){
		//Loop sound from last recording start (either record() or loop()) and begin a new recording
	}
	
	public void record(){
		//Similar to loop() but only used at beginning
	}
	
	

}
