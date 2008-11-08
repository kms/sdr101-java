package org.picofarad.sdr101;

import org.junit.Test;
import org.junit.Assert;

public class SplitterTest {
    @Test
    public void testOneWaySplitter() { 
	BufferSource bs = new BufferSource();
	bs.buffer(1.0);
	bs.buffer(0.5);
	bs.buffer(0.25);

	Splitter s = new Splitter(bs);

	Assert.assertEquals(1.0, s.out(), 0.0001);
	Assert.assertEquals(0.5, s.out(), 0.0001);
	Assert.assertEquals(0.25, s.out(), 0.0001);
    }

    @Test
    public void testTwoWaySplitter() { 
	BufferSource bs = new BufferSource();
	bs.buffer(1.0);
	bs.buffer(0.5);
	bs.buffer(0.25);

	Splitter sI = new Splitter(bs);
	Splitter sO1 = sI.newOutput();
	Splitter sO2 = sI.newOutput();

	Assert.assertEquals(1.0, sI.out(), 0.0001);
	Assert.assertEquals(1.0, sO2.out(), 0.0001);

	Assert.assertEquals(0.5, sI.out(), 0.0001);
	Assert.assertEquals(0.5, sO2.out(), 0.0001);

	Assert.assertEquals(0.25, sI.out(), 0.0001);
	Assert.assertEquals(0.25, sO2.out(), 0.0001);
    }

    @Test
    public void testThreeWaySplitter() { 
	Assert.fail();
    }
}
