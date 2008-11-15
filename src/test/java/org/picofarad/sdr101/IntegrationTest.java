package org.picofarad.sdr101;

import org.junit.Test;
import org.junit.Assert;

import org.picofarad.sdr101.blocks.FilterFactory;
import org.picofarad.sdr101.blocks.FirFilter;
import org.picofarad.sdr101.blocks.LocalOscillatorSource;
import org.picofarad.sdr101.blocks.Splitter;
import org.picofarad.sdr101.blocks.SignalBlock;

public class IntegrationTest {
    @Test
    public void testLPFilterStopBand() throws Exception {
	FirFilter ff = FilterFactory.loadFirFromFile("/firLP3kHzAt44100.txt");
	ff.setSource(LocalOscillatorSource.factory(44100, 20000));

	for (int i = 0; i < 44100 * 2; i++) {
	    Assert.assertEquals(0.0, ff.out(), 0.01);
	}
    }

    @Test
    public void testLPFilterPassBand() throws Exception {
	LocalOscillatorSource sineSource = LocalOscillatorSource.factory(44100, 100);
	Splitter s = new Splitter(sineSource);
	SignalBlock sinePristine = s.createOutput();

	FirFilter ff = FilterFactory.loadFirFromFile("/firLP3kHzAt44100.txt");
	ff.setSource(s.createOutput());

	for (int i = 0; i < ff.taps(); i++) {
	    sinePristine.out();
	    ff.out();
	}

	for (int i = 0; i < ff.taps() / 2; i++) {
	    ff.out();
	}

	for (int i = 0; i < 44100 * 2; i++) {
	    Assert.assertEquals(sinePristine.out(), ff.out(), 0.05);
	}
    }
}
