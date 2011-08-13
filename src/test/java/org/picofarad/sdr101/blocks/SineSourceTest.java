/* sdr101-java
 * Simple software-defined radio for Java.
 *
 * (c) Karl-Martin Skontorp <kms@skontorp.net> ~ http://22pf.org/
 * Licensed under the GNU GPL 2.0 or later.
 */

package org.picofarad.sdr101.blocks;

import org.junit.Assert;
import org.junit.Test;
import org.picofarad.sdr101.blocks.sources.SineSource;

public class SineSourceTest {
    @Test
    public void testOutputAt2Hz() {
        SineSource lo = new SineSource(8, 2);

        Assert.assertEquals(0.0, lo.output(), 0.001);
        Assert.assertEquals(1.0, lo.output(), 0.001);
        Assert.assertEquals(0.0, lo.output(), 0.001);
        Assert.assertEquals(-1.0, lo.output(), 0.001);
        Assert.assertEquals(0.0, lo.output(), 0.001);
        Assert.assertEquals(1.0, lo.output(), 0.001);
        Assert.assertEquals(0.0, lo.output(), 0.001);
        Assert.assertEquals(-1.0, lo.output(), 0.001);
    }

    @Test
    public void testOutputAtOneFourth() {
        SineSource lo = new SineSource(4, 1);

        Assert.assertEquals(0.0, lo.output(), 0.001);
        Assert.assertEquals(1.0, lo.output(), 0.001);
        Assert.assertEquals(0.0, lo.output(), 0.001);
        Assert.assertEquals(-1.0, lo.output(), 0.001);
    }

    @Test
    public void testOutputAtOneEight() {
        SineSource lo = new SineSource(8, 1);

        Assert.assertEquals(0.0, lo.output(), 0.001);
        Assert.assertEquals(0.707, lo.output(), 0.001);
        Assert.assertEquals(1.0, lo.output(), 0.001);
        Assert.assertEquals(0.707, lo.output(), 0.001);
        Assert.assertEquals(0.0, lo.output(), 0.001);
        Assert.assertEquals(-0.707, lo.output(), 0.001);
        Assert.assertEquals(-1.0, lo.output(), 0.001);
        Assert.assertEquals(-0.707, lo.output(), 0.001);
    }

    @Test
    public void testOutputWithMultipleCycles() {
        SineSource lo = new SineSource(4, 1);

        for (int i = 0; i < 1000; i++) {
            Assert.assertEquals(0.0, lo.output(), 0.001);
            Assert.assertEquals(1.0, lo.output(), 0.001);
            Assert.assertEquals(0.0, lo.output(), 0.001);
            Assert.assertEquals(-1.0, lo.output(), 0.001);
        }
    }

    @Test
    public void testOutput90PhaseOffset() {
        SineSource lo = new SineSource(8, 1, 90);

        Assert.assertEquals(1.0, lo.output(), 0.001);
        Assert.assertEquals(0.707, lo.output(), 0.001);
        Assert.assertEquals(0.0, lo.output(), 0.001);
        Assert.assertEquals(-0.707, lo.output(), 0.001);
        Assert.assertEquals(-1.0, lo.output(), 0.001);
        Assert.assertEquals(-0.707, lo.output(), 0.001);
        Assert.assertEquals(0.0, lo.output(), 0.001);
        Assert.assertEquals(0.707, lo.output(), 0.001);
    }

    @Test
    public void testOutput90PhaseOffsetAt2Hz() {
        SineSource lo = new SineSource(8, 2, 90);

        Assert.assertEquals(1.0, lo.output(), 0.001);
        Assert.assertEquals(0.0, lo.output(), 0.001);
        Assert.assertEquals(-1.0, lo.output(), 0.001);
        Assert.assertEquals(0.0, lo.output(), 0.001);
        Assert.assertEquals(1.0, lo.output(), 0.001);
        Assert.assertEquals(0.0, lo.output(), 0.001);
        Assert.assertEquals(-1.0, lo.output(), 0.001);
        Assert.assertEquals(0.0, lo.output(), 0.001);
    }

    @Test
    public void testOutput180PhaseOffset() {
        SineSource lo = new SineSource(4, 1, 180);

        Assert.assertEquals(0.0, lo.output(), 0.001);
        Assert.assertEquals(-1.0, lo.output(), 0.001);
        Assert.assertEquals(0.0, lo.output(), 0.001);
        Assert.assertEquals(1.0, lo.output(), 0.001);
    }

    @Test
    public void testOutputNeg90PhaseOffset() {
        SineSource lo = new SineSource(4, 1, -90);

        Assert.assertEquals(-1.0, lo.output(), 0.001);
        Assert.assertEquals(0.0, lo.output(), 0.001);
        Assert.assertEquals(1.0, lo.output(), 0.001);
        Assert.assertEquals(0.0, lo.output(), 0.001);
    }

    @Test
    public void testSetFrequency() {
        SineSource lo = new SineSource(8, 2);

        Assert.assertEquals(0.0, lo.output(), 0.001);
        Assert.assertEquals(1.0, lo.output(), 0.001);
        Assert.assertEquals(0.0, lo.output(), 0.001);
        Assert.assertEquals(-1.0, lo.output(), 0.001);

        lo.setFrequency(1);

        Assert.assertEquals(0.0, lo.output(), 0.001);
        Assert.assertEquals(1.0, lo.output(), 0.001);
        Assert.assertEquals(0.0, lo.output(), 0.001);
        Assert.assertEquals(-1.0, lo.output(), 0.001);

        Assert.assertEquals(0.0, lo.output(), 0.001);
        Assert.assertEquals(0.707, lo.output(), 0.001);
        Assert.assertEquals(1.0, lo.output(), 0.001);
    }
}
