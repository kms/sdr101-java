package org.picofarad.sdr101.blocks;

public class EnvelopeDetector implements SignalBlock {
    private SignalBlock source;

    public EnvelopeDetector(SignalBlock s, int l) {
	FullWaveRectifier fwr = new FullWaveRectifier(s);
	source = new CumulativeAverageFilter(fwr, l);
    }

    public double out() {
	return source.out();
    }
}
