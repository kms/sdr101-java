package org.picofarad.sdr101.blocks;

public class Summer implements SignalBlock {
    protected SignalBlock inputA;
    protected SignalBlock inputB;

    public Summer(SignalBlock a, SignalBlock b) {
        inputA = a;
        inputB = b;
    }

    public void setInputA(SignalBlock sb) {
        inputA = sb;
    }

    public void setInputB(SignalBlock sb) {
        inputB = sb;
    }

    public double output() {
        return inputA.output() + inputB.output();
    }
}
