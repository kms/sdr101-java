package org.picofarad.sdr101;

import org.junit.Test;
import org.junit.Assert;

public class LocalOscillatorSourceTest {
    @Test
    public void testOutAtOneFourth() {
	LocalOscillatorSource lo = new LocalOscillatorSource(4, 1);

	Assert.assertEquals(0.0, lo.out(), 0.0001);
	Assert.assertEquals(1.0, lo.out(), 0.0001);
	Assert.assertEquals(0.0, lo.out(), 0.0001);
	Assert.assertEquals(-1.0, lo.out(), 0.0001);
    }

    @Test
    public void testOutAtOneEight() {
	LocalOscillatorSource lo = new LocalOscillatorSource(8, 1);

	Assert.assertEquals(0.0, lo.out(), 0.001);
	Assert.assertEquals(0.707, lo.out(), 0.001);
	Assert.assertEquals(1.0, lo.out(), 0.001);
	Assert.assertEquals(0.707, lo.out(), 0.001);
	Assert.assertEquals(0.0, lo.out(), 0.001);
	Assert.assertEquals(-0.707, lo.out(), 0.001);
	Assert.assertEquals(-1.0, lo.out(), 0.001);
	Assert.assertEquals(-0.707, lo.out(), 0.001);
    }

    @Test
    public void testOutWithMultipleCycles() {
	LocalOscillatorSource lo = new LocalOscillatorSource(4, 1);

	for (int i = 0; i < 1000; i++) {
	    Assert.assertEquals(0.0, lo.out(), 0.0001);
	    Assert.assertEquals(1.0, lo.out(), 0.0001);
	    Assert.assertEquals(0.0, lo.out(), 0.0001);
	    Assert.assertEquals(-1.0, lo.out(), 0.0001);
	}
    }
}
