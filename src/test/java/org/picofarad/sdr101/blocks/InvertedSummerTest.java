/* sdr101-java
 * Simple software-defined radio for Java.
 *
 * (c) Karl-Martin Skontorp <kms@skontorp.net> ~ http://picofarad.org/
 * Licensed under the GNU GPL 2.0 or later.
 */

package org.picofarad.sdr101.blocks;

import org.junit.Assert;
import org.junit.Test;

public class InvertedSummerTest {
    @Test
    public void testInvertedSummer() {
        BufferSource a = new BufferSource();
        BufferSource b = new BufferSource();

        InvertedSummer s = new InvertedSummer(a, b);

        a.buffer(0.0);
        a.buffer(0.5);
        a.buffer(1.0);

        b.buffer(1.0);
        b.buffer(0.5);
        b.buffer(2.0);

        Assert.assertEquals(-1.0, s.output(), 0.0001);
        Assert.assertEquals(0.0, s.output(), 0.0001);
        Assert.assertEquals(-1.0, s.output(), 0.0001);
    }
}
