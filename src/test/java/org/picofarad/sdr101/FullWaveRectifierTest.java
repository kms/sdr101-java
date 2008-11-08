package org.picofarad.sdr101;

import org.junit.Test;
import org.junit.Assert;

public class FullWaveRectifierTest {
    @Test
    public void testFullWaveRectifier() {
	BufferSource bs = new BufferSource();

	bs.buffer(0.0);
	bs.buffer(1.0);
	bs.buffer(0.25);
	bs.buffer(-1.0);
	bs.buffer(-0.75);

	FullWaveRectifier fwr = new FullWaveRectifier(bs);

	Assert.assertEquals(0.0, fwr.out(), 0.0001);
	Assert.assertEquals(1.0, fwr.out(), 0.0001);
	Assert.assertEquals(0.25, fwr.out(), 0.0001);
	Assert.assertEquals(1.0, fwr.out(), 0.0001);
	Assert.assertEquals(0.75, fwr.out(), 0.0001);
    }
}
