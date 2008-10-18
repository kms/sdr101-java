package org.picofarad.sdr101;

import java.util.List;
import java.util.ArrayList;

public class FirFilter {
    private List<Double> coefficients;
    private List<Double> buffer;

    public FirFilter(List<Double> f) {
	buffer = new ArrayList<Double>();
	coefficients = f;
    }

    public void padBuffer() {
	while (buffer.size() < coefficients.size()) {
	    buffer.add(0.0);
	}
    }

    public void in(double d) {
	buffer.add(d);
    }

    public double out() {
	double d = 0.0;

	padBuffer();

	for (int i = 0; i < coefficients.size(); i++) {
	    d += buffer.get(i) * coefficients.get(i);
	}

	buffer.remove(0);

	return d;
    }

    public int bufferSize() {
	return buffer.size();
    }
}
