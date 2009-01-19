package org.picofarad.sdr101.blocks;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Deque;
import java.util.ArrayDeque;

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
