import javax.sound.midi.Instrument;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;

public class GestureHandler {
	private Synth cow = new Synth();
	private int instrument;
	private int numInstruments = 5;
	private int prevPitch;
	private Synthesizer synthesizer;
	private int[] instruments;
	
	public GestureHandler(){
		cow = new Synth();
		synthesizer = null;

		instruments = new int[numInstruments];

		instruments[0]=39;
		instruments[1]=19;
		instruments[2]=63;
		instruments[3]=87;
		instruments[4]=55;
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

		int octaves = 0;
		if(instruments[instrument] == 39){
			octaves = 24;
		}


		if(pitch == 1){

			cow.setPitch(60-octaves);
		}else if(pitch == 2){
			cow.setPitch(63 - octaves);
		}else if(pitch == 3){
			cow.setPitch(65 - octaves);
		}else if(pitch == 4){
			cow.setPitch(67 - octaves);
		}else if(pitch >= 5){
			cow.setPitch(70 - octaves);
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
				cow.stopNote(synthesizer);
			//-}

			if (cow.getIsPlaying()) {
				synthesizer = cow.playNote();
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
