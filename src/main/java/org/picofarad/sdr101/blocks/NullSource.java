package org.picofarad.sdr101.blocks;

public class NullSource implements SignalBlock {
    public NullSource() {
    }

    public double out() {
	return 0.0;
    }
}
