/* sdr101-java
 * Simple software-defined radio for Java.
 *
 * (c) Karl-Martin Skontorp <kms@skontorp.net> ~ http://picofarad.org/
 * Licensed under the GNU GPL 2.0 or later.
 */

package org.picofarad.sdr101;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.picofarad.sdr101.blocks.FilterFactory;
import org.picofarad.sdr101.blocks.FirFilter;
import org.picofarad.sdr101.blocks.InvertedSummer;
import org.picofarad.sdr101.blocks.Mixer;
import org.picofarad.sdr101.blocks.SignalBlock;
import org.picofarad.sdr101.blocks.Splitter;
import org.picofarad.sdr101.blocks.Summer;
import org.picofarad.sdr101.blocks.sources.SineSource;

public class IntegrationTest {
    @Test
    public void testHPFilterStopBand() throws Exception {
        int fs = 44100;
        FirFilter ff = FilterFactory.loadFirFromFile("/firHP300HzAt44100-51taps.txt");
        ff.setInput(new SineSource(fs, 1));

        for (int i = 0; i < (fs * 2); i++) {
            Assert.assertEquals(0.0, ff.output(), 0.5);
        }
    }

    @Test
    public void testHPFilterPassBand() throws Exception {
        int fs = 44100;
        SineSource sineSource = new SineSource(fs, 2000);
        Splitter s = new Splitter(sineSource);
        SignalBlock sinePristine = s.createOutput();

        FirFilter ff = FilterFactory.loadFirFromFile("/firHP300HzAt44100-51taps.txt");
        ff.setInput(s.createOutput());

        for (int i = 0; i < ff.taps(); i++) {
            sinePristine.output();
            ff.output();
        }

        for (int i = 0; i < ff.taps() / 2; i++) {
            ff.output();
        }

        for (int i = 0; i < (fs * 2); i++) {
            Assert.assertEquals(sinePristine.output(), ff.output(), 0.05);
        }
    }

    @Test
    public void testLPFilterStopBand() throws Exception {
        int fs = 44100;
        FirFilter ff = FilterFactory.loadFirFromFile("/firLP3kHzAt44100-101taps.txt");
        ff.setInput(new SineSource(fs, 20000));

        for (int i = 0; i < (fs * 2); i++) {
            Assert.assertEquals(0.0, ff.output(), 0.01);
        }
    }

    @Test
    public void testLPFilterPassBand() throws Exception {
        int fs = 44100;
        SineSource sineSource = new SineSource(fs, 100);
        Splitter s = new Splitter(sineSource);
        SignalBlock sinePristine = s.createOutput();

        FirFilter ff = FilterFactory.loadFirFromFile("/firLP3kHzAt44100-101taps.txt");
        ff.setInput(s.createOutput());

        for (int i = 0; i < ff.taps(); i++) {
            sinePristine.output();
            ff.output();
        }

        for (int i = 0; i < ff.taps() / 2; i++) {
            ff.output();
        }

        for (int i = 0; i < (fs * 2); i++) {
            Assert.assertEquals(sinePristine.output(), ff.output(), 0.05);
        }
    }

    @Test
    public void testHilbertFilter() throws Exception {
        int fs = 44100;
        SineSource sine = new SineSource(fs, 3000, 0);
        SineSource desired = new SineSource(fs, 3000, -90);

        FirFilter ff = FilterFactory.loadFirFromFile("/firHilbert-31taps.txt");
        ff.setInput(sine);

        for (int i = 0; i < ff.taps(); i++) {
            desired.output();
            ff.output();
        }

        for (int i = 0; i < ff.taps() / 2; i++) {
            ff.output();
        }

        for (int j = 0; j < fs * 2; j++) {
            Assert.assertEquals(desired.output(), ff.output(), 0.01);
        }
    }

    @Test
    public void testGenerateCarrierUSBInvertedSummer() {
        int fs = 44100;
        SineSource i = new SineSource(fs, 100, 0);
        SineSource q = new SineSource(fs, 100, 90);
        SineSource loI = new SineSource(fs, 1000, 0);
        SineSource loQ = new SineSource(fs, 1000, 90);
        SineSource desired = new SineSource(fs, 1100, 270);

        Mixer mI = new Mixer(i, loI);
        Mixer mQ = new Mixer(q, loQ);
        InvertedSummer s = new InvertedSummer(mI, mQ);

        for (int j = 0; j < (fs * 2); j++) {
            Assert.assertEquals(desired.output(), s.output(), 0.0001);
        }
    }

    @Test
    public void testGenerateCarrierUSBSwappedLOs() {
        int fs = 44100;
        SineSource i = new SineSource(fs, 100, 0);
        SineSource q = new SineSource(fs, 100, 90);
        SineSource loI = new SineSource(fs, 1000, 0);
        SineSource loQ = new SineSource(fs, 1000, 90);
        SineSource desired = new SineSource(fs, 1100, 0);

        Mixer mI = new Mixer(i, loQ);
        Mixer mQ = new Mixer(q, loI);
        Summer s = new Summer(mI, mQ);

        for (int j = 0; j < (fs * 2); j++) {
            Assert.assertEquals(desired.output(), s.output(), 0.0001);
        }
    }

    @Test
    public void testGenerateCarrierLSB() {
        int fs = 44100;
        SineSource i = new SineSource(fs, 100, 0);
        SineSource q = new SineSource(fs, 100, 90);
        SineSource loI = new SineSource(fs, 1000, 0);
        SineSource loQ = new SineSource(fs, 1000, 90);
        SineSource desired = new SineSource(fs, 900, 90);

        Mixer mI = new Mixer(i, loI);
        Mixer mQ = new Mixer(q, loQ);
        Summer s = new Summer(mI, mQ);

        for (int j = 0; j < (fs * 2); j++) {
            Assert.assertEquals(desired.output(), s.output(), 0.0001);
        }
    }

    @Test
    public void testGenerateTwoToneUSB() throws IOException {
        int fs = 44100;
        SineSource afAI = new SineSource(fs, 100, 0);
        SineSource afBI = new SineSource(fs, 300, 0);
        Summer afI = new Summer(afAI, afBI);
        
        SineSource afAQ = new SineSource(fs, 100, 90);
        SineSource afBQ = new SineSource(fs, 300, 90);
        Summer afQ = new Summer(afAQ, afBQ);
                
        SineSource loI = new SineSource(fs, 1000, 0);
        SineSource loQ = new SineSource(fs, 1000, 90);

        SineSource desiredA = new SineSource(fs, 1100, 0);
        SineSource desiredB = new SineSource(fs, 1300, 0);
        Summer desired = new Summer(desiredA, desiredB);
        
        Mixer mI = new Mixer(afI, loQ);
        Mixer mQ = new Mixer(afQ, loI);
        Summer s = new Summer(mI, mQ);

        for (int j = 0; j < (fs * 2); j++) {
           Assert.assertEquals(desired.output(), s.output(), 0.0001);
        }
    }
    
    @Test
    public void testDemodulateUSB() throws Exception {
        int fs = 44100;
        SineSource i = new SineSource(fs, 15100, 0);
        SineSource q = new SineSource(fs, 15100, 90);
        SineSource loI = new SineSource(fs, 15000, 0);
        SineSource loQ = new SineSource(fs, 15000, 90);
        SineSource desired = new SineSource(fs, 100, 90);

        Mixer mI = new Mixer(i, loI);
        Mixer mQ = new Mixer(q, loQ);
        Summer s = new Summer(mI, mQ);
        FirFilter lpf = FilterFactory.loadFirFromFile("/firLP3kHzAt44100-101taps.txt");
        lpf.setInput(s);

        for (int j = 0; j < lpf.taps(); j++) {
            desired.output();
            lpf.output();
        }

        for (int j = 0; j < lpf.taps() / 2; j++) {
            lpf.output();
        }

        for (int j = 0; j < (fs * 2); j++) {
            Assert.assertEquals(desired.output(), lpf.output(), 0.01);
        }
    }

    //@Test
    public void testDemodulateTwoToneUSB() throws Exception {
        int fs = 44100;
        SineSource rfAI = new SineSource(fs, 15100, 0);
        SineSource rfBI = new SineSource(fs, 16000, 0);
        Summer rfI = new Summer(rfAI, rfBI);
        
        SineSource rfAQ = new SineSource(fs, 15100, 90);
        SineSource rfBQ = new SineSource(fs, 16000, 90);
        Summer rfQ = new Summer(rfAQ, rfBQ);
                
        SineSource loI = new SineSource(fs, 15000, 0);
        SineSource loQ = new SineSource(fs, 15000, 90);

        SineSource desiredA = new SineSource(fs, 100, 0);
        SineSource desiredB = new SineSource(fs, 1000, 0);
        Summer desired = new Summer(desiredA, desiredB);
                
        Mixer mI = new Mixer(rfI, loI);
        Mixer mQ = new Mixer(rfQ, loQ);
        Summer s = new Summer(mI, mQ);
        FirFilter lpf = FilterFactory.loadFirFromFile("/firLP3kHzAt44100-101taps.txt");
        lpf.setInput(s);

        for (int j = 0; j < lpf.taps(); j++) {
            desired.output();
            lpf.output();
        }

        for (int j = 0; j < lpf.taps() / 2; j++) {
            lpf.output();
        }

        for (int j = 0; j < (fs * 2); j++) {
            Assert.assertEquals(desired.output(), lpf.output(), 0.01);
        }
    }
}