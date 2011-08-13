/* sdr101-java
 * Simple software-defined radio for Java.
 *
 * (c) Karl-Martin Skontorp <kms@skontorp.net> ~ http://22pf.org/
 * Licensed under the GNU GPL 2.0 or later.
 */

package org.picofarad.sdr101.blocks;

import org.junit.Assert;
import org.junit.Test;
import org.picofarad.sdr101.blocks.sources.BufferSource;

public class FullWaveRectifierTest {
    @Test
    public void testFullWaveRectifier() {
        BufferSource bs = new BufferSource();

        bs.add(0.0);
        bs.add(1.0);
        bs.add(0.25);
        bs.add(-1.0);
        bs.add(-0.75);

        FullWaveRectifier fwr = new FullWaveRectifier(bs);

        Assert.assertEquals(0.0, fwr.output(), 0.0001);
        Assert.assertEquals(1.0, fwr.output(), 0.0001);
        Assert.assertEquals(0.25, fwr.output(), 0.0001);
        Assert.assertEquals(1.0, fwr.output(), 0.0001);
        Assert.assertEquals(0.75, fwr.output(), 0.0001);
    }
}
