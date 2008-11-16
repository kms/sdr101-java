package org.picofarad.sdr101.blocks;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Deque;
import java.util.ArrayDeque;

public class FirFilter implements SignalBlock {
    private Double[] coefficients;
    private Deque<Double> buffer;
    private SignalBlock source;

    public FirFilter(List<Double> f) {
	buffer = new ArrayDeque<Double>();
	coefficients = f.toArray(new Double[0]);
	source = new NullSource();
	fillBuffer();
    }

    public void setSource(SignalBlock sb) {
	source = sb;
    }

    public void fillBuffer() {
        while (buffer.size() < taps()) {
	    buffer.add(source.out());
	}
    }

    public int taps() {
	return coefficients.length;
    }

    public double out() {
	double d = 0.0;

	buffer.offerFirst(source.out());
	buffer.removeLast();

	int i = 0;
	for (Double b : buffer) {
	    d += b * coefficients[i];
	    i++;
	}

	return d;
    }
}
