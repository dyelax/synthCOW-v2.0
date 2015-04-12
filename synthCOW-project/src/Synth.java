import javax.sound.midi.*;

public class Synth {
	
	private int pitch;
	private boolean isPlaying;
	private int volume;
	private int instrument;


	public Synth(){
		pitch = 60;
        isPlaying = false;
        volume = 0;

	}

    public Synthesizer playBeat(int length){


        try {
            Synthesizer synth = MidiSystem.getSynthesizer();
            synth.open();
            MidiChannel[] channels = synth.getChannels();

            channels[9].programChange(instrument);
            channels[9].noteOn( pitch, volume );
            this.delay(length);
            channels[9].noteOff( pitch);
            return synth;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

	public Synthesizer playNote(){
		
//        try {
//
//            Sequencer sequencer = MidiSystem.getSequencer();
//            sequencer.open();
//            Sequence sequence = new Sequence(Sequence.PPQ,4);
//            Track track = sequence.createTrack();
//
//            MidiEvent event = null;
//
//            ShortMessage first = new ShortMessage();
//            first.setMessage(192,1,instrument,0);
//            MidiEvent changeInstrument = new MidiEvent(first, 1);
//            track.add(changeInstrument);
//
//            ShortMessage a = new ShortMessage();
//            a.setMessage(144,1,pitch,volume);
//            MidiEvent noteOn = new MidiEvent(a, 1);
//            track.add(noteOn);
//
//            ShortMessage b = new ShortMessage();
//            b.setMessage(128,1,pitch,volume);
//            MidiEvent noteOff = new MidiEvent(b, Integer.MAX_VALUE);
//            track.add(noteOff);
//
//            sequencer.setSequence(sequence);
//            sequencer.start();
//
//            return sequencer;
//
//        } catch (Exception ex) {
//        	ex.printStackTrace();
//        	return null;
//        }

        try {
            Synthesizer synth = MidiSystem.getSynthesizer();
            synth.open();
            MidiChannel[] channels = synth.getChannels();

            channels[4].programChange(instrument);
            channels[4].noteOn( pitch, volume );
            return synth;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }
	
	public void stopNote(Synthesizer synthesizer){
        if(synthesizer != null) {
            for (MidiChannel chan : synthesizer.getChannels()) {
                chan.noteOff(pitch);
            }
            synthesizer.close();
        }
    }

    public void delay(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean getIsPlaying(){ return isPlaying; }
    
    public void setIsPlaying(boolean isPlaying){
    	this.isPlaying = isPlaying;
    }

    public int getPitch(){ return pitch; }

    public void setPitch(int pitch){
    	this.pitch = pitch;
    }

    public int getVolume(){ return volume; }

    public void setVolume(int volume){
    	this.volume = volume;
    }

    public int getInstrument(){ return instrument; }
    
    public void setInstrument(int instrument){
    	this.instrument = instrument;
    }


}
