package org.picofarad.sdr101.blocks;

import org.junit.Test;
import org.junit.Assert;

public class NullSourceTest {
    @Test
    public void testOut() {
	NullSource ns = new NullSource();

	for (int i = 0; i < 1000; i++) {
	    Assert.assertEquals(0.00, ns.out(), 0.0001);
	}
    }
}
