package org.picofarad.sdr101.blocks;

public class Summer implements SignalBlock {
    private SignalBlock sourceA;
    private SignalBlock sourceB;

    public Summer(SignalBlock a, SignalBlock b) {
	sourceA = a;
	sourceB = b;
    }

    public void setSourceA(SignalBlock sb) {
	sourceA = sb;
    }

    public void setSourceB(SignalBlock sb) {
	sourceB = sb;
    }

    public double out() {
	return sourceA.out() + sourceB.out();
    }
}
