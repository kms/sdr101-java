package org.picofarad.sdr101.blocks;

import org.junit.Test;
import org.junit.Assert;

public class EnvelopeDetectorTest {
    @Test
    public void testEnvelopeDetector() {
	SineSource lo = SineSource.factory(44100, 1000);
	EnvelopeDetector ed = new EnvelopeDetector(lo, 44100);

	for (int i = 0; i < 44100; i++) {
	    ed.out();
	}

	for (int i = 0; i < 100; i++) {
	    //Assert.assertEquals(0.707, ed.out(), 0.001);
	}
    }
}
