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

public class SplitterTest {
    @Test
    public void testOneWaySplitter() {
        BufferSource bs = new BufferSource();
        bs.buffer(1.0);
        bs.buffer(0.5);
        bs.buffer(0.25);

        Splitter s = new Splitter(bs);
        SplitterOutput o1 = s.createOutput();

        Assert.assertEquals(1.0, o1.output(), 0.0001);
        Assert.assertEquals(0.5, o1.output(), 0.0001);
        Assert.assertEquals(0.25, o1.output(), 0.0001);
    }

    @Test
    public void testTwoWaySplitter() {
        BufferSource bs = new BufferSource();
        bs.buffer(1.0);
        bs.buffer(0.5);
        bs.buffer(0.25);

        Splitter s = new Splitter(bs);
        SplitterOutput o1 = s.createOutput();
        SplitterOutput o2 = s.createOutput();

        Assert.assertEquals(1.0, o1.output(), 0.0001);
        Assert.assertEquals(1.0, o2.output(), 0.0001);

        Assert.assertEquals(0.5, o1.output(), 0.0001);
        Assert.assertEquals(0.5, o2.output(), 0.0001);

        Assert.assertEquals(0.25, o1.output(), 0.0001);
        Assert.assertEquals(0.25, o2.output(), 0.0001);
    }

    @Test
    public void testSplitterWithUnorderedAccess() {
        BufferSource bs = new BufferSource();
        bs.buffer(1.0);
        bs.buffer(0.5);
        bs.buffer(0.25);

        Splitter s = new Splitter(bs);
        SplitterOutput o1 = s.createOutput();
        SplitterOutput o2 = s.createOutput();
        SplitterOutput o3 = s.createOutput();

        Assert.assertEquals(1.0, o3.output(), 0.0001);

        Assert.assertEquals(1.0, o1.output(), 0.0001);
        Assert.assertEquals(0.5, o1.output(), 0.0001);
        Assert.assertEquals(0.25, o1.output(), 0.0001);

        Assert.assertEquals(1.0, o2.output(), 0.0001);
        Assert.assertEquals(0.5, o2.output(), 0.0001);
        Assert.assertEquals(0.25, o2.output(), 0.0001);

        Assert.assertEquals(0.5, o3.output(), 0.0001);
        Assert.assertEquals(0.25, o3.output(), 0.0001);
    }

    @Test
    public void testCascadedSplitters() {
        BufferSource bs = new BufferSource();
        bs.buffer(1.0);
        bs.buffer(0.5);
        bs.buffer(0.25);

        Splitter sA = new Splitter(bs);
        SplitterOutput oA1 = sA.createOutput();
        SplitterOutput oA2 = sA.createOutput();

        Splitter sB = new Splitter(oA1);
        SplitterOutput oB2 = sB.createOutput();

        Assert.assertEquals(1.0, oB2.output(), 0.0001);
        Assert.assertEquals(0.5, oB2.output(), 0.0001);
        Assert.assertEquals(0.25, oB2.output(), 0.0001);

        Assert.assertEquals(1.0, oA2.output(), 0.0001);
        Assert.assertEquals(0.5, oA2.output(), 0.0001);
        Assert.assertEquals(0.25, oA2.output(), 0.0001);
    }
}
