package org.picofarad.sdr101.blocks;

public class FullWaveRectifier implements SignalBlock {
    private SignalBlock input;

    public FullWaveRectifier(SignalBlock sb) {
	input = sb;
    }

    public double output() {
	return Math.abs(input.output());
    }
}
