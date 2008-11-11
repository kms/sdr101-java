package org.picofarad.sdr101.blocks;

public class SplitterOutput implements SignalBlock {
    private Splitter splitter;

    public SplitterOutput(Splitter s) {
	splitter = s;
    }

    public double out() {
	return splitter.out(this);
    }
}
