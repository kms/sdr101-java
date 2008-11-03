package org.picofarad.sdr101;

import org.junit.Test;
import org.junit.Assert;

public class ImpulseSourceTest {
    @Test
    public void testOut() {
	ImpulseSource is = new ImpulseSource();

	Assert.assertEquals(1.00, is.out(), 0.0001);

	for (int i = 0; i < 1000; i++) {
	    Assert.assertEquals(0.00, is.out(), 0.0001);
	}
    }
}
