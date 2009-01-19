package org.picofarad.sdr101.blocks;

public class CumulativeAverageFilter implements SignalBlock {
    private SignalBlock input;
    private int length;
    private double sum;

    public CumulativeAverageFilter(SignalBlock s, int l) {
        input = s;
        length = l;
        sum = 0.0;
    }

    public double output() {
        sum -= sum / length;
        sum += input.output() / length;

        return sum;
    }
}
