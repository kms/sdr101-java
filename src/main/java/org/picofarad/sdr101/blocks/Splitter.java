/* sdr101-java
 * Simple software-defined radio for Java.
 *
 * (c) Karl-Martin Skontorp <kms@skontorp.net> ~ http://picofarad.org/
 * Licensed under the GNU GPL 2.0 or later.
 */

package org.picofarad.sdr101.blocks;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Splitter {
    private SignalBlock input;
    private Map<SplitterOutput, Deque<Double>> outputs;

    public Splitter(SignalBlock s) {
        input = s;
        outputs = new HashMap<SplitterOutput, Deque<Double>>();
    }

    public SplitterOutput createOutput() {
        SplitterOutput so = new SplitterOutput(this);
        outputs.put(so, new ArrayDeque<Double>());

        return so;
    }

    private void fillAllBuffers() {
        double sample = input.output();

        for (Deque<Double> buffer : outputs.values()) {
            buffer.offer(sample);
        }
    }

    public double output(SplitterOutput so) {
        Deque<Double> buffer = outputs.get(so);

        if (buffer.size() == 0) {
            fillAllBuffers();
        }

        return buffer.remove();
    }
}
