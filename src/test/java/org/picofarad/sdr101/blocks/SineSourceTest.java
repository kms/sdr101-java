package org.picofarad.sdr101.blocks;

import org.junit.Test;
import org.junit.Assert;

public class SineSourceTest {
    @Test
    public void testOutAt2Hz() {
	SineSource lo = new SineSource(8, 2);

	Assert.assertEquals(0.0, lo.output(), 0.0001);
	Assert.assertEquals(1.0, lo.output(), 0.0001);
	Assert.assertEquals(0.0, lo.output(), 0.0001);
	Assert.assertEquals(-1.0, lo.output(), 0.0001);
	Assert.assertEquals(0.0, lo.output(), 0.0001);
	Assert.assertEquals(1.0, lo.output(), 0.0001);
	Assert.assertEquals(0.0, lo.output(), 0.0001);
	Assert.assertEquals(-1.0, lo.output(), 0.0001);
    }

    @Test
    public void testOutAtOneFourth() {
	SineSource lo = new SineSource(4, 1);

	Assert.assertEquals(0.0, lo.output(), 0.0001);
	Assert.assertEquals(1.0, lo.output(), 0.0001);
	Assert.assertEquals(0.0, lo.output(), 0.0001);
	Assert.assertEquals(-1.0, lo.output(), 0.0001);
    }

    @Test
    public void testOutAtOneEight() {
	SineSource lo = new SineSource(8, 1);

	Assert.assertEquals(0.0, lo.output(), 0.001);
	Assert.assertEquals(0.707, lo.output(), 0.001);
	Assert.assertEquals(1.0, lo.output(), 0.001);
	Assert.assertEquals(0.707, lo.output(), 0.001);
	Assert.assertEquals(0.0, lo.output(), 0.001);
	Assert.assertEquals(-0.707, lo.output(), 0.001);
	Assert.assertEquals(-1.0, lo.output(), 0.001);
	Assert.assertEquals(-0.707, lo.output(), 0.001);
    }

    @Test
    public void testOutWithMultipleCycles() {
	SineSource lo = new SineSource(4, 1);

	for (int i = 0; i < 1000; i++) {
	    Assert.assertEquals(0.0, lo.output(), 0.0001);
	    Assert.assertEquals(1.0, lo.output(), 0.0001);
	    Assert.assertEquals(0.0, lo.output(), 0.0001);
	    Assert.assertEquals(-1.0, lo.output(), 0.0001);
	}
    }

    @Test
    public void testOut90PhaseOffset() {
	SineSource lo = new SineSource(8, 1, 90);

	Assert.assertEquals(1.0, lo.output(), 0.001);
	Assert.assertEquals(0.707, lo.output(), 0.001);
	Assert.assertEquals(0.0, lo.output(), 0.001);
	Assert.assertEquals(-0.707, lo.output(), 0.001);
	Assert.assertEquals(-1.0, lo.output(), 0.001);
	Assert.assertEquals(-0.707, lo.output(), 0.001);
	Assert.assertEquals(0.0, lo.output(), 0.001);
	Assert.assertEquals(0.707, lo.output(), 0.001); 
    }

    @Test
    public void testOut90PhaseOffsetAt2Hz() {
	SineSource lo = new SineSource(8, 2, 90);

	Assert.assertEquals(1.0, lo.output(), 0.001);
	Assert.assertEquals(0.0, lo.output(), 0.001);
	Assert.assertEquals(-1.0, lo.output(), 0.001);
	Assert.assertEquals(0.0, lo.output(), 0.001);
	Assert.assertEquals(1.0, lo.output(), 0.001);
	Assert.assertEquals(0.0, lo.output(), 0.001);
	Assert.assertEquals(-1.0, lo.output(), 0.001);
	Assert.assertEquals(0.0, lo.output(), 0.001);
    }

    @Test
    public void testOut180PhaseOffset() {
	SineSource lo = new SineSource(4, 1, 180);

	Assert.assertEquals(0.0, lo.output(), 0.0001);
	Assert.assertEquals(-1.0, lo.output(), 0.0001);
	Assert.assertEquals(0.0, lo.output(), 0.0001);
	Assert.assertEquals(1.0, lo.output(), 0.0001);
    }

    @Test
    public void testOutNeg90PhaseOffset() {
	SineSource lo = new SineSource(4, 1, -90);

	Assert.assertEquals(-1.0, lo.output(), 0.0001);
	Assert.assertEquals(0.0, lo.output(), 0.0001);
	Assert.assertEquals(1.0, lo.output(), 0.0001);
	Assert.assertEquals(0.0, lo.output(), 0.0001);
    }
}
