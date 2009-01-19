package org.picofarad.sdr101.blocks;

public class InvertedSummer extends Summer {
    public InvertedSummer(SignalBlock a, SignalBlock b) {
        super(a, b);
    }

    public double output() {
        return inputA.output() - inputB.output();
    }
}
