/* sdr101-java
 * Simple software-defined radio for Java.
 *
 * (c) Karl-Martin Skontorp <kms@skontorp.net> ~ http://22pf.org/
 * Licensed under the GNU GPL 2.0 or later.
 */

package org.picofarad.sdr101.blocks;

import org.junit.Assert;
import org.junit.Test;
import org.picofarad.sdr101.blocks.sources.NullSource;

public class NullSourceTest {
    @Test
    public void testOutput() {
        NullSource ns = new NullSource();

        for (int i = 0; i < 1000; i++) {
            Assert.assertEquals(0.00, ns.output(), 0.0001);
        }
    }
}
