package org.picofarad.sdr101;

public class FullWaveRectifier implements SignalBlock {
    private SignalBlock source;

    public FullWaveRectifier(SignalBlock s) {
	source = s;
    }

    public double out() {
	return Math.abs(source.out());
    }
}
