/* sdr101-java
 * Simple software-defined radio for Java.
 *
 * (c) Karl-Martin Skontorp <kms@skontorp.net> ~ http://picofarad.org/
 * Licensed under the GNU GPL 2.0 or later.
 */

package org.picofarad.sdr101.blocks;

import org.junit.Assert;
import org.junit.Test;
import org.picofarad.sdr101.blocks.sources.BufferSource;

public class BufferSourceTest {
    @Test
    public void testBufferSourceEmpty() {
        BufferSource bs = new BufferSource();

        for (int i = 0; i < 100; i++) {
            Assert.assertEquals(0.0, bs.output(), 0.0001);
        }
    }

    @Test
    public void testBufferSourceWithData() {
        BufferSource bs = new BufferSource();

        bs.add(1.0);
        bs.add(0.5);
        bs.add(0.0);
        bs.add(0.6);

        Assert.assertEquals(1.0, bs.output(), 0.0001);
        Assert.assertEquals(0.5, bs.output(), 0.0001);
        Assert.assertEquals(0.0, bs.output(), 0.0001);
        Assert.assertEquals(0.6, bs.output(), 0.0001);
    }

    @Test
    public void testBufferSourceGoingEmpty() {
        BufferSource bs = new BufferSource();

        bs.add(1.0);
        bs.add(0.5);

        Assert.assertEquals(1.0, bs.output(), 0.0001);
        Assert.assertEquals(0.5, bs.output(), 0.0001);

        for (int i = 0; i < 100; i++) {
            Assert.assertEquals(0.0, bs.output(), 0.0001);
        }
    }
}
