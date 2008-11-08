package org.picofarad.sdr101;

public class Splitter implements SignalBlock {
    private SignalBlock source;
    private Splitter splitter;
    private double lastSample;

    public Splitter(SignalBlock s) {
	source = s;
    }

    public Splitter(Splitter s) {
	splitter = s;
    }

    public void setSource(SignalBlock sb) {
	source = sb;
    }

    public double getLastSample() {
	return lastSample;
    }

    public Splitter newOutput() {
	return new Splitter(this);
    }

    public double out() {
	if (splitter != null) {
	    return splitter.getLastSample();
	} else {
	    lastSample = source.out();
	    return lastSample;
	}
    }
}
