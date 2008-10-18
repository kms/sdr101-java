package org.picofarad.sdr101;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Assert;

public class FirFilterTest {
    @Test
    public void testCreate() {
	List<Double> c = new ArrayList();
	c.add(0.0);

	FirFilter ff = new FirFilter(c);
	Assert.assertNotNull(ff);
    }

    @Test
    public void testInOut() {
	List<Double> c = new ArrayList();
	c.add(0.0);

	FirFilter ff = new FirFilter(c);

	Assert.assertEquals(0, ff.bufferSize());
	ff.in(0.0);
	Assert.assertEquals(1, ff.bufferSize());
	ff.in(0.0);
	Assert.assertEquals(2, ff.bufferSize());
	ff.out();
	Assert.assertEquals(1, ff.bufferSize());
	ff.out();
	Assert.assertEquals(0, ff.bufferSize());
    }

    @Test
    public void testImpulseResponse() {
	List<Double> c = new ArrayList<Double>();
	c.add(0.0);
	c.add(0.1);
	c.add(1.0);
	c.add(-0.1);
	c.add(-1.0);

	FirFilter ff = new FirFilter(c);
	ff.in(1);
	ff.in(0);
	ff.in(0);
	ff.in(0);
	ff.in(0);

	Assert.assertEquals(ff.bufferSize(), c.size());

	for (int i = 0; i < ff.bufferSize(); i++) {
	    Assert.assertEquals(c.get(i), ff.out(), 0.01);
	}
    }
}
