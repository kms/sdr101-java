/* sdr101-java
 * Simple software-defined radio for Java.
 *
 * (c) Karl-Martin Skontorp <kms@skontorp.net> ~ http://picofarad.org/
 * Licensed under the GNU GPL 2.0 or later.
 */

package org.picofarad.sdr101.blocks;

public class InvertedSummer extends Summer {
    public InvertedSummer(SignalBlock a, SignalBlock b) {
        super(a, b);
    }

    public double output() {
        return inputA.output() - inputB.output();
    }
}
