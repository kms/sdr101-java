package org.picofarad.sdr101;

public class Mixer implements SignalBlock {
    private SignalBlock sourceA;
    private SignalBlock sourceB;

    public Mixer(SignalBlock a, SignalBlock b) {
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
	return sourceA.out() * sourceB.out();
    }
}
