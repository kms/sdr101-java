package org.picofarad.sdr101;


public class LocalOscillatorSource implements SignalBlock {
    private int sampleRate;
    private double frequency;
    private int sampleNumber;

    public LocalOscillatorSource(int sr, double f) {
	sampleRate = sr;
	frequency = f;
    }

    public double out() {
	double r;
	double position = ((double) sampleNumber) / ((double) sampleRate);

	r = Math.sin(2 * Math.PI * position * frequency);

	sampleNumber++;
	if (sampleNumber == sampleRate) {
	    sampleNumber = 0;
	}

	return r;
    }
}
