/* sdr101-java
 * Simple software-defined radio for Java.
 *
 * (c) Karl-Martin Skontorp <kms@skontorp.net> ~ http://picofarad.org/
 * Licensed under the GNU GPL 2.0 or later.
 */

package org.picofarad.sdr101;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.Mixer.Info;

import org.picofarad.sdr101.blocks.FilterFactory;
import org.picofarad.sdr101.blocks.FirFilter;
import org.picofarad.sdr101.blocks.Summer;
import org.picofarad.sdr101.blocks.sources.ByteArraySource;
import org.picofarad.sdr101.blocks.sources.SineSource;

public class Sdr101cli {
    public static void main(String args[]) throws Exception {
        Mixer.Info mi[] = AudioSystem.getMixerInfo();
        Mixer.Info mixerSdr = null;
        Mixer.Info mixerAf = null;

        for (Info element : mi) {
            System.out.println(element.getName());
            if (element.getName().equals("default")) {
                mixerSdr = element;
            }
            if (element.getName().equals("default")) {
                mixerAf = element;
            }
        }

        mixerSdr = mi[0];
        mixerAf = mi[0];

        AudioFormat formatSdrRx = new AudioFormat(44100, 16, 2, true, true);
        AudioFormat formatAfSpeaker = new AudioFormat(44100, 16, 2, true, true);

        DataLine.Info infoSdrRx = null;
        DataLine.Info infoAfSpeaker = null;
        infoSdrRx = new DataLine.Info(TargetDataLine.class, formatSdrRx);
        infoAfSpeaker = new DataLine.Info(SourceDataLine.class, formatAfSpeaker);

        TargetDataLine lineSdrRx;
        SourceDataLine lineAfSpeaker;

        lineSdrRx = (TargetDataLine) AudioSystem.getMixer(mixerSdr).getLine(
                infoSdrRx);
        lineAfSpeaker = (SourceDataLine) AudioSystem.getMixer(mixerAf).getLine(
                infoAfSpeaker);
        lineSdrRx.open(formatSdrRx);
        lineAfSpeaker.open(formatAfSpeaker);

        int bufferSize = 400;
        byte[] dataSdrRx = new byte[bufferSize];
        byte[] dataAfSpeaker = new byte[bufferSize];

        lineSdrRx.start();
        lineAfSpeaker.start();

        int fs = 44100;
        SineSource loI = new SineSource(fs, 2000, 0);
        SineSource loQ = new SineSource(fs, 2000, 90);
        ByteArraySource basI = new ByteArraySource(dataSdrRx, 0, 1);
        ByteArraySource basQ = new ByteArraySource(dataSdrRx, 2, 3);

        org.picofarad.sdr101.blocks.Mixer mI = new org.picofarad.sdr101.blocks.Mixer(
                basI, loI);
        org.picofarad.sdr101.blocks.Mixer mQ = new org.picofarad.sdr101.blocks.Mixer(
                basQ, loQ);

        Summer summer = new Summer(mI, mQ);
        FirFilter lpf = FilterFactory.loadFirFromFile("/firLP3kHzAt44100.txt");
        lpf.setInput(summer);

        while (true) {
            for (int j = 0; j < bufferSize; j += 4) {
                basI.setHighIndex(j);
                basI.setLowIndex(j + 1);
                basQ.setHighIndex(j + 2);
                basQ.setLowIndex(j + 3);
                double d = summer.output();
                int nSample = (int) Math.round(d * 32766.0);
                byte high = (byte) ((nSample >> 8) & 0xFF);
                byte low = (byte) (nSample & 0xFF);
                dataAfSpeaker[j + 0] = high;
                dataAfSpeaker[j + 1] = low;
                // d = ();
                // d = 0.0;
                // nSample = (int) Math.round(d * 32766.0);
                // high = (byte) ((nSample >> 8) & 0xFF);
                // low = (byte) (nSample & 0xFF);
                dataAfSpeaker[j + 2] = high;
                dataAfSpeaker[j + 3] = low;
            }
            lineAfSpeaker.write(dataAfSpeaker, 0, bufferSize);
        }

        /*
         * System.out.println("SDR101-Java"); System.out.println();
         * 
         * BufferedReader inputReader = new BufferedReader(new
         * InputStreamReader(System.in));
         * 
         * while (true) { System.out.print("> "); System.out.flush(); String
         * input = inputReader.readLine().trim();
         * 
         * if (input.equals("+")) { // Increase LO } else if (input.equals("-"))
         * { // Decrease LO } else if (input.equals("++")) { // Increase LO }
         * else if (input.equals("--")) { // Decrease LO } else if
         * (input.equals("")) { // Print status } else if (input.equals("q")) {
         * System.exit(0); } }
         */
    }
}
