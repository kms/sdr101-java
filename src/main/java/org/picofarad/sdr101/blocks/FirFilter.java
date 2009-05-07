/* sdr101-java
 * Simple software-defined radio for Java.
 *
 * (c) Karl-Martin Skontorp <kms@skontorp.net> ~ http://picofarad.org/
 * Licensed under the GNU GPL 2.0 or later.
 */

package org.picofarad.sdr101.blocks;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class FirFilter implements SignalBlock {
    private Double[] coefficients;
    private Deque<Double> buffer;
    private SignalBlock input;

    public FirFilter(List<Double> f) {
        buffer = new ArrayDeque<Double>();
        coefficients = f.toArray(new Double[0]);
        setInput(new NullSource());
    }

    public void setInput(SignalBlock sb) {
        input = sb;
        fillBuffer();
    }

    public int taps() {
        return coefficients.length;
    }

    private void fillBuffer() {
        while (buffer.size() < taps()) {
            buffer.add(input.output());
        }
    }

    public double output() {
        double d = 0.0;

        buffer.offerFirst(input.output());
        buffer.removeLast();

        int i = 0;
        for (Double b : buffer) {
            d += b * coefficients[i];
            i++;
        }

        return d;
    }
}
