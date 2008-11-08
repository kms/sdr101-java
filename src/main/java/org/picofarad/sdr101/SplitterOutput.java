package org.picofarad.sdr101;

public class SplitterOutput implements SignalBlock {
    private Splitter splitter;

    public SplitterOutput(Splitter s) {
	splitter = s;
    }

    public double out() {
	return splitter.out(this);
    }
}
