package org.picofarad.sdr101.blocks;

import org.junit.Test;
import org.junit.Assert;

public class InvertedSummerTest {
    @Test
    public void testInvertedSummer() { 
	BufferSource a = new BufferSource();
	BufferSource b = new BufferSource();

	InvertedSummer s = new InvertedSummer(a, b);

	a.buffer(0.0);
	a.buffer(0.5);
	a.buffer(1.0);

	b.buffer(1.0);
	b.buffer(0.5);
	b.buffer(2.0);

	Assert.assertEquals(-1.0, s.output(), 0.0001);
	Assert.assertEquals(0.0, s.output(), 0.0001);
	Assert.assertEquals(-1.0, s.output(), 0.0001);
    }
}
