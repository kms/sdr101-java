package org.picofarad.sdr101.blocks;

import org.junit.Test;
import org.junit.Assert;

public class ImpulseSourceTest {
    @Test
    public void testOutput() {
	ImpulseSource is = new ImpulseSource();

	Assert.assertEquals(1.00, is.output(), 0.0001);

	for (int i = 0; i < 1000; i++) {
	    Assert.assertEquals(0.00, is.output(), 0.0001);
	}
    }
}
