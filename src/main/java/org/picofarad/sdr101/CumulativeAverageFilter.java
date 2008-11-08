package org.picofarad.sdr101;

public class CumulativeAverageFilter implements SignalBlock {
    private SignalBlock source;
    private int length;
    private double sum;

    public CumulativeAverageFilter(SignalBlock s, int l) {
	source = s;
	length = l;
	sum = 0.0;
    }

    public double out() {
	sum -= sum / length;
	sum += source.out() / length;

	return sum;
    }
}
