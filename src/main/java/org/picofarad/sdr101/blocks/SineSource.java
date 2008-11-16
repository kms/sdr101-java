package org.picofarad.sdr101.blocks;

public class SineSource implements SignalBlock {
    private int sampleRate;
    private double frequency;
    private double phaseOffset;
    private int sampleNumber;

    public SineSource(int sr, double f) {
	sampleRate = sr;
	frequency = f;
	phaseOffset = 0.0;
    }

    public SineSource(int sr, double f, double p) {
	sampleRate = sr;
	frequency = f;
	phaseOffset = p;
    }

    public double output() {
	double r;
	double position = (double) sampleNumber / sampleRate;
	double offset = (phaseOffset / 360) / frequency;

	r = Math.sin(2 * Math.PI * (position + offset) * frequency);

	sampleNumber++;
	if (sampleNumber == sampleRate) {
	    sampleNumber = 0;
	}

	return r;
    }
}
