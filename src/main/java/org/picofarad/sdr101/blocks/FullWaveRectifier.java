/* sdr101-java
 * Simple software-defined radio for Java.
 *
 * (c) Karl-Martin Skontorp <kms@skontorp.net> ~ http://picofarad.org/
 * Licensed under the GNU GPL 2.0 or later.
 */

package org.picofarad.sdr101.blocks;

public class FullWaveRectifier implements SignalBlock {
    private SignalBlock input;

    public FullWaveRectifier(SignalBlock sb) {
        input = sb;
    }

    public double output() {
        return Math.abs(input.output());
    }
}
