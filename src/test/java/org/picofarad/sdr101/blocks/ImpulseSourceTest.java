/* sdr101-java
 * Simple software-defined radio for Java.
 *
 * (c) Karl-Martin Skontorp <kms@skontorp.net> ~ http://picofarad.org/
 * Licensed under the GNU GPL 2.0 or later.
 */

package org.picofarad.sdr101.blocks;

import org.junit.Assert;
import org.junit.Test;

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
