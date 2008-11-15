package org.picofarad.sdr101.blocks;

public class Summer implements SignalBlock {
    private SignalBlock sourceA;
    private SignalBlock sourceB;
    private boolean invert;

    public Summer(SignalBlock a, SignalBlock b) {
	sourceA = a;
	sourceB = b;
	invert = false;
    }

    public Summer(SignalBlock a, SignalBlock b, boolean i) {
	sourceA = a;
	sourceB = b;
	invert = i;
    }

    public void setSourceA(SignalBlock sb) {
	sourceA = sb;
    }

    public void setSourceB(SignalBlock sb) {
	sourceB = sb;
    }

    public double out() {
	if (invert) {
	    return sourceA.out() - sourceB.out();
	} else {
	    return sourceA.out() + sourceB.out();
	}
    }
}
