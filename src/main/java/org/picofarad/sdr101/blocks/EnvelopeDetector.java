package org.picofarad.sdr101.blocks;

public class EnvelopeDetector implements SignalBlock {
    private SignalBlock input;

    public EnvelopeDetector(SignalBlock s, int l) {
	FullWaveRectifier fwr = new FullWaveRectifier(s);
	input = new CumulativeAverageFilter(fwr, l);
    }

    public double output() {
	return input.output();
    }
}
