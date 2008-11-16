package org.picofarad.sdr101.blocks;

import org.junit.Test;
import org.junit.Assert;

public class CumulativeAverageFilterTest {
    @Test
    public void testCumulativeAverageFilterSimple() {
	BufferSource bs = new BufferSource();

	bs.buffer(0.0);
	bs.buffer(1.0);
	bs.buffer(0.0);
	bs.buffer(1.0);
	bs.buffer(1.0);
	bs.buffer(1.0);
	bs.buffer(1.0);
	bs.buffer(1.0);
	bs.buffer(1.0);
	bs.buffer(1.0);
	bs.buffer(1.0);
	bs.buffer(1.0);

	CumulativeAverageFilter maf = new CumulativeAverageFilter(bs, 2);

	Assert.assertEquals(0.0, maf.output(), 0.001);
	Assert.assertEquals(0.5, maf.output(), 0.001);
	Assert.assertEquals(0.25, maf.output(), 0.001);
	Assert.assertEquals(0.625, maf.output(), 0.001);
	Assert.assertEquals(0.8125, maf.output(), 0.001);
	maf.output();
	maf.output();
	maf.output();
	maf.output();
	maf.output();
	maf.output();
	Assert.assertEquals(1.0, maf.output(), 0.1);
    }

    @Test
    public void testCumulativeAverageFilterSinus() {
	SineSource lo = SineSource.factory(44100, 1000);
	CumulativeAverageFilter maf = new CumulativeAverageFilter(lo, 44100);

	for (int i = 0; i < 44100; i++) {
	    maf.output();
	}

	for (int i = 0; i < 100; i++) {
	    Assert.assertEquals(0.0, maf.output(), 0.001);
	}
    }
}
