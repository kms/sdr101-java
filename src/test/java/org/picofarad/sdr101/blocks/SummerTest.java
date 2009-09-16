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

public class SummerTest {
    @Test
    public void testSummer() {
        BufferSource a = new BufferSource();
        BufferSource b = new BufferSource();

        Summer s = new Summer(a, b);

        a.add(0.0);
        a.add(0.5);
        a.add(1.0);

        b.add(1.0);
        b.add(0.5);
        b.add(2.0);

        Assert.assertEquals(1.0, s.output(), 0.0001);
        Assert.assertEquals(1.0, s.output(), 0.0001);
        Assert.assertEquals(3.0, s.output(), 0.0001);
    }
}
