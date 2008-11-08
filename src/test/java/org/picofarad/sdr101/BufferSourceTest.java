package org.picofarad.sdr101;

import org.junit.Test;
import org.junit.Assert;

public class BufferSourceTest {
    @Test
    public void testBufferSourceEmpty() { 
	BufferSource bs = new BufferSource();

	for (int i = 0; i < 100; i++) {
	    Assert.assertEquals(0.0, bs.out(), 0.0001);
	}
    }

    @Test
    public void testBufferSourceWithData() {
	BufferSource bs = new BufferSource();

	bs.buffer(1.0);
	bs.buffer(0.5);
	bs.buffer(0.0);
	bs.buffer(0.6);

	Assert.assertEquals(1.0, bs.out(), 0.0001);
	Assert.assertEquals(0.5, bs.out(), 0.0001);
	Assert.assertEquals(0.0, bs.out(), 0.0001);
	Assert.assertEquals(0.6, bs.out(), 0.0001);
    }

    @Test
    public void testBufferSourceGoingEmpty() {
	BufferSource bs = new BufferSource();
	
	bs.buffer(1.0);
	bs.buffer(0.5);
	
	Assert.assertEquals(1.0, bs.out(), 0.0001);
	Assert.assertEquals(0.5, bs.out(), 0.0001);

	for (int i = 0; i < 100; i++) {
	    Assert.assertEquals(0.0, bs.out(), 0.0001);
	}
    }
}
