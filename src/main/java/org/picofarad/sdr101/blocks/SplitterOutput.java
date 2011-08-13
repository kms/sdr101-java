/* sdr101-java
 * Simple software-defined radio for Java.
 *
 * (c) Karl-Martin Skontorp <kms@skontorp.net> ~ http://22pf.org/
 * Licensed under the GNU GPL 2.0 or later.
 */

package org.picofarad.sdr101.blocks;

public class SplitterOutput implements SignalBlock {
    private Splitter splitter;

    public SplitterOutput(Splitter s) {
        splitter = s;
    }

    public double output() {
        return splitter.output(this);
    }
}
