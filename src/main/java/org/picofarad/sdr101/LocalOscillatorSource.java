package org.picofarad.sdr101;


public class LocalOscillatorSource implements SignalBlock {
    private int sampleRate;
    private double frequency;
    private double phaseOffset;
    private int sampleNumber;

    public static LocalOscillatorSource factory(int sr, double f, double p) {
	return new LocalOscillatorSource(sr, f, p);
    }

    public static LocalOscillatorSource factory(int sr, double f) {
	return new LocalOscillatorSource(sr, f, 0.0);
    }

    private LocalOscillatorSource(int sr, double f, double p) {
	sampleRate = sr;
	frequency = f;
	phaseOffset = p;
    }

    public double out() {
	double r;
	double position = (double) sampleNumber / sampleRate;
	double offset = phaseOffset / 360;

	r = Math.sin(2 * Math.PI * (position + offset) * frequency);

	sampleNumber++;
	if (sampleNumber == sampleRate) {
	    sampleNumber = 0;
	}

	return r;
    }
}
