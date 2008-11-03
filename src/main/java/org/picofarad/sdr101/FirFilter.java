package org.picofarad.sdr101;

import java.util.List;
import java.util.ArrayList;

public class FirFilter implements SignalBlock {
    private List<Double> coefficients;
    private List<Double> buffer;
    private SignalBlock source;

    public FirFilter(List<Double> f) {
	buffer = new ArrayList<Double>();
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

	buffer.add(0, source.out());
	buffer.remove(buffer.size() - 1);

	for (int i = 0; i < coefficients.size(); i++) {
	    d += buffer.get(i) * coefficients.get(i);
	}

	return d;
    }
}
