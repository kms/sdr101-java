package org.picofarad.sdr101;

import org.picofarad.sdr101.blocks.FilterFactory;
import org.picofarad.sdr101.blocks.FirFilter;
import org.picofarad.sdr101.blocks.SineSource;
import org.picofarad.sdr101.blocks.Splitter;
import org.picofarad.sdr101.blocks.SplitterOutput;
import org.picofarad.sdr101.blocks.SignalBlock;
import org.picofarad.sdr101.blocks.Summer;
import org.picofarad.sdr101.blocks.Mixer;

public class Sdr101performance {
    public static void main(String args[]) throws Exception {
	int fs = 44100;
	SineSource i = new SineSource(fs, 1100, 0);
	SineSource q = new SineSource(fs, 1100, 90);
	SineSource loI = new SineSource(fs, 1000, 0);
	SineSource loQ = new SineSource(fs, 1000, 90);
	SineSource desired = new SineSource(fs, 100, 90);

	Mixer mI = new Mixer(i, loI);
	Mixer mQ = new Mixer(q, loQ);
	Summer s = new Summer(mI, mQ);
	FirFilter ff = FilterFactory.loadFirFromFile("/firLP3kHzAt44100.txt");
	ff.setInput(s);
	Splitter spl = new Splitter(ff);
	SplitterOutput so = spl.createOutput();

	for (int j = 0; j < fs * 60; j++) {
	    so.output();
	}
    }
}
