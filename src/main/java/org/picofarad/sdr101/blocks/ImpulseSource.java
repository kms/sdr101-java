package org.picofarad.sdr101.blocks;

public class ImpulseSource implements SignalBlock {
    private boolean first;

    public ImpulseSource() {
	first = true;
    }

    public double output() {
	if (first) {
	    first = false;
	    return 1.0;
	} else {
	    return 0.0;
	}
    }
}
