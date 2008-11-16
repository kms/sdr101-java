package org.picofarad.sdr101.blocks;

public class NullSource implements SignalBlock {
    public double output() {
	return 0.0;
    }
}
