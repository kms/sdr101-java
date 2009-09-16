/* sdr101-java
 * Simple software-defined radio for Java.
 *
 * (c) Karl-Martin Skontorp <kms@skontorp.net> ~ http://picofarad.org/
 * Licensed under the GNU GPL 2.0 or later.
 */

package org.picofarad.sdr101.blocks.sources;

import org.picofarad.sdr101.blocks.SignalBlock;

public class NullSource implements SignalBlock {
    public double output() {
        return 0.0;
    }
}
