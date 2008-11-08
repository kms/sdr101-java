package org.picofarad.sdr101;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class FirFilter implements SignalBlock {
    private List<Double> coefficients;
    private LinkedList<Double> buffer;
    private SignalBlock source;

    public FirFilter(List<Double> f) {
	buffer = new LinkedList<Double>();
	coefficients = f;
	source = new NullSource();
	fillBuffer();
    }

    public void setSource(SignalBlock sb) {
	source = sb;
    }

    public void fillBuffer() {
        while (buffer.size() < coefficients.size()) {
	    buffer.add(source.out());
	}
    }

    public double out() {
	double d = 0.0;

	buffer.addFirst(source.out());
	buffer.removeLast();

	for (int i = 0; i < coefficients.size(); i++) {
	    d += buffer.get(i) * coefficients.get(i);
	}

	return d;
    }
}
