package org.picofarad.sdr101.blocks;

import org.junit.Test;
import org.junit.Assert;

public class LocalOscillatorSourceTest {
    @Test
    public void testOutAtOneFourth() {
	LocalOscillatorSource lo = LocalOscillatorSource.factory(4, 1);

	Assert.assertEquals(0.0, lo.out(), 0.0001);
	Assert.assertEquals(1.0, lo.out(), 0.0001);
	Assert.assertEquals(0.0, lo.out(), 0.0001);
	Assert.assertEquals(-1.0, lo.out(), 0.0001);
    }

    @Test
    public void testOutAtOneEight() {
	LocalOscillatorSource lo = LocalOscillatorSource.factory(8, 1);

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
	LocalOscillatorSource lo = LocalOscillatorSource.factory(4, 1);

	for (int i = 0; i < 1000; i++) {
	    Assert.assertEquals(0.0, lo.out(), 0.0001);
	    Assert.assertEquals(1.0, lo.out(), 0.0001);
	    Assert.assertEquals(0.0, lo.out(), 0.0001);
	    Assert.assertEquals(-1.0, lo.out(), 0.0001);
	}
    }

    @Test
    public void testOut90PhaseOffset() {
	LocalOscillatorSource lo = LocalOscillatorSource.factory(4, 1, 90);

	Assert.assertEquals(1.0, lo.out(), 0.0001);
	Assert.assertEquals(0.0, lo.out(), 0.0001);
	Assert.assertEquals(-1.0, lo.out(), 0.0001);
	Assert.assertEquals(0.0, lo.out(), 0.0001);
    }

    @Test
    public void testOut180PhaseOffset() {
	LocalOscillatorSource lo = LocalOscillatorSource.factory(4, 1, 180);

	Assert.assertEquals(0.0, lo.out(), 0.0001);
	Assert.assertEquals(-1.0, lo.out(), 0.0001);
	Assert.assertEquals(0.0, lo.out(), 0.0001);
	Assert.assertEquals(1.0, lo.out(), 0.0001);
    }

    @Test
    public void testOutNeg90PhaseOffset() {
	LocalOscillatorSource lo = LocalOscillatorSource.factory(4, 1, -90);

	Assert.assertEquals(-1.0, lo.out(), 0.0001);
	Assert.assertEquals(0.0, lo.out(), 0.0001);
	Assert.assertEquals(1.0, lo.out(), 0.0001);
	Assert.assertEquals(0.0, lo.out(), 0.0001);
    }

}
