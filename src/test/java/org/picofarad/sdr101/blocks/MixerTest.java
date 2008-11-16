package org.picofarad.sdr101.blocks;

import org.junit.Test;
import org.junit.Assert;

public class MixerTest {
    @Test
    public void testMixer() { 
	BufferSource a = new BufferSource();
	BufferSource b = new BufferSource();

	Mixer m = new Mixer(a, b);

	a.buffer(0.0);
	a.buffer(0.5);
	a.buffer(1.0);

	b.buffer(1.0);
	b.buffer(0.5);
	b.buffer(2.0);

	Assert.assertEquals(0.0, m.output(), 0.0001);
	Assert.assertEquals(0.25, m.output(), 0.0001);
	Assert.assertEquals(2.0, m.output(), 0.0001);
    }
}
