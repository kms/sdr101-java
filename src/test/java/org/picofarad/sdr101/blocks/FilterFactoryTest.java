package org.picofarad.sdr101.blocks;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Test;
import org.junit.Assert;

public class FilterFactoryTest {
    @Test
    public void testLoadCoefficientsFromFile() throws IOException {
	List<Double> c = FilterFactory.loadCoefficientsFromFile("/firTest.txt");
	Assert.assertNotNull(c);
	Assert.assertEquals(4, c.size());
	Assert.assertEquals(1.0, c.get(0), 0.0001);
	Assert.assertEquals(2.0, c.get(1), 0.0001);
	Assert.assertEquals(0.12345, c.get(2), 0.0001);
	Assert.assertEquals(-0.5, c.get(3), 0.0001);
    }

    @Test
    public void testLoadCoefficientsFromFileScientificNotation() throws IOException {
	List<Double> c = FilterFactory.loadCoefficientsFromFile("/firTestScientific.txt");
	Assert.assertNotNull(c);
	Assert.assertEquals(5, c.size());
	Assert.assertEquals(1.0, c.get(0), 0.0001);
	Assert.assertEquals(2.0, c.get(1), 0.0001);
	Assert.assertEquals(0.12345, c.get(2), 0.0001);
	Assert.assertEquals(-0.5, c.get(3), 0.0001);
	Assert.assertEquals(-1.0, c.get(4), 0.0001);
    }

    @Test
    public void testLoadFirFromFileBadData() {
	try {
	    SignalBlock sb = FilterFactory.loadFirFromFile("/firTestBadData.txt");
	    Assert.fail("Did not throw exception.");
	} catch (IOException e) {
	}
    }

    @Test
    public void testLoadFirFromFileBadFilename() throws IOException {
	try {
	    SignalBlock sb = FilterFactory.loadFirFromFile("/thisFileDoesNotExist.txt");
	    Assert.fail("Did not throw exception.");
	} catch (FileNotFoundException e) {
	}
    }

    @Test
    public void testLoadFirFromFile() throws IOException {
	FirFilter ff = FilterFactory.loadFirFromFile("/firTest.txt");
	Assert.assertNotNull(ff);

	ff.setSource(new ImpulseSource());
	Assert.assertEquals(1.0, ff.out(), 0.0001);
	Assert.assertEquals(2.0, ff.out(), 0.0001);
	Assert.assertEquals(0.12345, ff.out(), 0.0001);
	Assert.assertEquals(-0.5, ff.out(), 0.0001);
    }

    @Test
    public void testLPFilterStopBand() throws IOException {
	FirFilter ff = FilterFactory.loadFirFromFile("/firLP3kHzAt44100.txt");
	ff.setSource(LocalOscillatorSource.factory(44100, 20000));

	for (int i = 0; i < 44100 * 2; i++) {
	    Assert.assertEquals(0.0, ff.out(), 0.01);
	}
    }

    @Test
    public void testLPFilterPassBand() throws IOException {
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
