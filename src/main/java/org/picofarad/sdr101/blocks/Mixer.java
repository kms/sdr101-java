/* sdr101-java
 * Simple software-defined radio for Java.
 *
 * (c) Karl-Martin Skontorp <kms@skontorp.net> ~ http://22pf.org/
 * Licensed under the GNU GPL 2.0 or later.
 */

package org.picofarad.sdr101.blocks;

public class Mixer implements SignalBlock {
    private SignalBlock inputA;
    private SignalBlock inputB;

    public Mixer(SignalBlock a, SignalBlock b) {
        inputA = a;
        inputB = b;
    }

    public double output() {
        return inputA.output() * inputB.output();
    }
}
