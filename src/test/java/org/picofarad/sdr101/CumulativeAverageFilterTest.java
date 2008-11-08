package org.picofarad.sdr101;

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

	Assert.assertEquals(0.0, maf.out(), 0.001);
	Assert.assertEquals(0.5, maf.out(), 0.001);
	Assert.assertEquals(0.25, maf.out(), 0.001);
	Assert.assertEquals(0.625, maf.out(), 0.001);
	Assert.assertEquals(0.8125, maf.out(), 0.001);
	maf.out();
	maf.out();
	maf.out();
	maf.out();
	maf.out();
	maf.out();
	Assert.assertEquals(1.0, maf.out(), 0.1);
    }

    @Test
    public void testCumulativeAverageFilterSinus() {
	LocalOscillatorSource lo = LocalOscillatorSource.factory(44100, 1000);
	CumulativeAverageFilter maf = new CumulativeAverageFilter(lo, 44100);

	for (int i = 0; i < 44100; i++) {
	    maf.out();
	}

	for (int i = 0; i < 100; i++) {
	    Assert.assertEquals(0.0, maf.out(), 0.001);
	}
    }
}
