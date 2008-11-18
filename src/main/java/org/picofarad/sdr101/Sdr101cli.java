package org.picofarad.sdr101;

import org.picofarad.sdr101.blocks.SineSource;
import org.picofarad.sdr101.blocks.Summer;
import org.picofarad.sdr101.blocks.ByteArraySource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ByteArrayOutputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Mixer.Info;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Line;

public class Sdr101cli {
    public static void main(String args[]) throws Exception {
	Mixer.Info mi[] = AudioSystem.getMixerInfo();
	Mixer.Info mixerSdr = null;
	Mixer.Info mixerAf = null;

	for (int i = 0; i < mi.length; i++) {
	    System.out.println(mi[i].getName());
	    if (mi[i].getName().equals("default")) {
		mixerSdr = mi[i];
	    }
	    if (mi[i].getName().equals("default")) {
		mixerAf = mi[i];
	    }
	}

	mixerSdr = mi[2];
	mixerAf = mi[2];

	AudioFormat formatSdrRx = new AudioFormat(44100, 16, 1, true, true);
	AudioFormat formatAfSpeaker = new AudioFormat(44100, 16, 2, true, true);

	DataLine.Info infoSdrRx = null;
	DataLine.Info infoAfSpeaker = null;
	infoSdrRx = new DataLine.Info(TargetDataLine.class, formatSdrRx); 
	infoAfSpeaker = new DataLine.Info(SourceDataLine.class, formatAfSpeaker); 

	TargetDataLine lineSdrRx;
	SourceDataLine lineAfSpeaker;

	lineSdrRx = (TargetDataLine) AudioSystem.getMixer(mixerSdr).getLine(infoSdrRx);
	lineAfSpeaker = (SourceDataLine) AudioSystem.getMixer(mixerAf).getLine(infoAfSpeaker);
	lineSdrRx.open(formatSdrRx);
	lineAfSpeaker.open(formatAfSpeaker);

	int numBytesRead;
	byte[] data = new byte[400];

	lineSdrRx.start();
	lineAfSpeaker.start();

	int fs = 44100;
	SineSource loI = new SineSource(fs, 8000, 0);
	SineSource loQ = new SineSource(fs, 8000, 90);
	ByteArraySource basI = new ByteArraySource(data, 0, 1);
	ByteArraySource basQ = new ByteArraySource(data, 2, 3);

	org.picofarad.sdr101.blocks.Mixer mI = new org.picofarad.sdr101.blocks.Mixer(basI, loI);
	org.picofarad.sdr101.blocks.Mixer mQ = new org.picofarad.sdr101.blocks.Mixer(basQ, loQ);

	Summer summer = new Summer(mI, mQ);

	while (true) {
	    numBytesRead = lineSdrRx.read(data, 0, 2);
	    int j = 0;
	    //for (int j = 0; j < data.length; j += 4) {
		double d = summer.output();
		int nSample = (int) Math.round(d * 32766.0);
		byte high = (byte) ((nSample >> 8) & 0xFF);
		byte low = (byte) (nSample & 0xFF);
		data[j + 0] = high;
		data[j + 1] = low;
		data[j + 2] = high;
		data[j + 3] = low;

	  //  }
	    lineAfSpeaker.write(data, 0, 4);
	}

	/*
	   System.out.println("SDR101-Java");
	   System.out.println();

	   BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

	   while (true) {
	   System.out.print("> ");
	   System.out.flush();
	   String input = inputReader.readLine().trim();

	   if (input.equals("+")) {
	// Increase LO
	} else if (input.equals("-")) {
	// Decrease LO
	} else if (input.equals("++")) {
	// Increase LO
	} else if (input.equals("--")) {
	// Decrease LO
	} else if (input.equals("")) {
	// Print status
	} else if (input.equals("q")) {
	System.exit(0);
	}
	   }
	   */
    }
}
