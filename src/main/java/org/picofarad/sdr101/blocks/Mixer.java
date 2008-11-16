package org.picofarad.sdr101.blocks;

public class Mixer implements SignalBlock {
    private SignalBlock inputA;
    private SignalBlock inputB;

    public Mixer(SignalBlock a, SignalBlock b) {
	inputA = a;
	inputB = b;
    }

    public double output() {
	return inputA.output() * inputB.output();
    }
}
