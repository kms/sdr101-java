package org.picofarad.sdr101.blocks;

public class SineSource implements SignalBlock {
    private int sampleRate;
    private double frequency;
    private double phaseOffset;
    private int sampleNumber;

    public static SineSource factory(int sr, double f, double p) {
	return new SineSource(sr, f, p);
    }

    public static SineSource factory(int sr, double f) {
	return new SineSource(sr, f, 0.0);
    }

    private SineSource(int sr, double f, double p) {
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
