import javax.sound.midi.Sequence;

/**
 * Created by jpatsenker on 4/12/15.
 */
public class BeatMachine {

    Synth syn;


    public BeatMachine(){
        syn = new Synth();
    }



    public void beat1(){

        syn.setPitch(60);
        syn.setVolume(100);


        //Bass
        syn.setInstrument(36);

        //System.out.println(syn.getInstrument());


        syn.playBeat(250);

        //OHH
        syn.setInstrument(46);

        syn.playBeat(250);

        //Snare
        syn.setInstrument(38);


        syn.playBeat(250);

        //OHH
        syn.setInstrument(46);

        syn.playBeat(250);

        //CHH
        syn.setInstrument(42);

        syn.playBeat(250);

        //OHH
        syn.setInstrument(46);

        syn.playBeat(250);

        //Snare
        syn.setInstrument(38);

        syn.playBeat(250);

        //OHH
        syn.setInstrument(46);

        syn.playBeat(250);







        //Bass, Open hi hat, snare, OHH, Closed hi hat, OHH, snare, OHH


    }


}
