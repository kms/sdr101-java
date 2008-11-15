package org.picofarad.sdr101;

import org.junit.Test;
import org.junit.Assert;

import org.picofarad.sdr101.blocks.FilterFactory;
import org.picofarad.sdr101.blocks.FirFilter;
import org.picofarad.sdr101.blocks.SineSource;
import org.picofarad.sdr101.blocks.Splitter;
import org.picofarad.sdr101.blocks.SignalBlock;
import org.picofarad.sdr101.blocks.Summer;
import org.picofarad.sdr101.blocks.Mixer;

public class IntegrationTest {
    @Test
    public void testLPFilterStopBand() throws Exception {
	int fs = 44100;
	FirFilter ff = FilterFactory.loadFirFromFile("/firLP3kHzAt44100.txt");
	ff.setSource(SineSource.factory(fs, 20000));

	for (int i = 0; i < fs * 2; i++) {
	    Assert.assertEquals(0.0, ff.out(), 0.01);
	}
    }

    @Test
    public void testLPFilterPassBand() throws Exception {
	int fs = 44100;
	SineSource sineSource = SineSource.factory(fs, 100);
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

	for (int i = 0; i < fs * 2; i++) {
	    Assert.assertEquals(sinePristine.out(), ff.out(), 0.05);
	}
    }

    @Test
    public void testGenerateCarrierUSBInvertedSummer() {
	int fs = 44100;
	SineSource i = SineSource.factory(fs, 100, 0);
	SineSource q = SineSource.factory(fs, 100, 90);
	SineSource loI = SineSource.factory(fs, 1000, 0);
	SineSource loQ = SineSource.factory(fs, 1000, 90);
	SineSource desired = SineSource.factory(fs, 1100, 270);

	Mixer mI = new Mixer(i, loI);
	Mixer mQ = new Mixer(q, loQ);
	Summer s = new Summer(mI, mQ, true);

	for (int j = 0; j < fs * 2; j++) {
	    Assert.assertEquals(desired.out(), s.out(), 0.0001);
	}
    }

    @Test
    public void testGenerateCarrierUSBSwappedLOs() {
	int fs = 44100;
	SineSource i = SineSource.factory(fs, 100, 0);
	SineSource q = SineSource.factory(fs, 100, 90);
	SineSource loI = SineSource.factory(fs, 1000, 0);
	SineSource loQ = SineSource.factory(fs, 1000, 90);
	SineSource desired = SineSource.factory(fs, 1100, 0);

	Mixer mI = new Mixer(i, loQ);
	Mixer mQ = new Mixer(q, loI);
	Summer s = new Summer(mI, mQ);

	for (int j = 0; j < fs * 2; j++) {
	    Assert.assertEquals(desired.out(), s.out(), 0.0001);
	}
    }

    @Test
    public void testGenerateCarrierLSB() {
	int fs = 44100;
	SineSource i = SineSource.factory(fs, 100, 0);
	SineSource q = SineSource.factory(fs, 100, 90);
	SineSource loI = SineSource.factory(fs, 1000, 0);
	SineSource loQ = SineSource.factory(fs, 1000, 90);
	SineSource desired = SineSource.factory(fs, 900, 90);

	Mixer mI = new Mixer(i, loI);
	Mixer mQ = new Mixer(q, loQ);
	Summer s = new Summer(mI, mQ);

	for (int j = 0; j < fs * 2; j++) {
	    Assert.assertEquals(desired.out(), s.out(), 0.0001);
	}
    }

    @Test
    public void testDemodulateUSB() throws Exception {
	int fs = 44100;
	SineSource i = SineSource.factory(fs, 1100, 0);
	SineSource q = SineSource.factory(fs, 1100, 90);
	SineSource loI = SineSource.factory(fs, 1000, 0);
	SineSource loQ = SineSource.factory(fs, 1000, 90);
	SineSource desired = SineSource.factory(fs, 100, 90);

	Mixer mI = new Mixer(i, loI);
	Mixer mQ = new Mixer(q, loQ);
	Summer s = new Summer(mI, mQ);
	FirFilter ff = FilterFactory.loadFirFromFile("/firLP3kHzAt44100.txt");
	ff.setSource(s);

	for (int j = 0; j < ff.taps(); j++) {
	    desired.out();
	    ff.out();
	}

	for (int j = 0; j < ff.taps() / 2; j++) {
	    ff.out();
	}

	for (int j = 0; j < fs * 2; j++) {
	    Assert.assertEquals(desired.out(), ff.out(), 0.01);
	}
    }
}
