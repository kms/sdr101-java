/* sdr101-java
 * Simple software-defined radio for Java.
 *
 * (c) Karl-Martin Skontorp <kms@skontorp.net> ~ http://picofarad.org/
 * Licensed under the GNU GPL 2.0 or later.
 */

package org.picofarad.sdr101.blocks.sources;

import org.picofarad.sdr101.blocks.SignalBlock;

public class SineSource implements SignalBlock {
    private int sampleRate;
    private double frequency;
    private Double newFrequency;
    private double phaseOffset;
    private int sampleNumber;

    public SineSource(int sr, double f) {
        sampleRate = sr;
        frequency = f;
        phaseOffset = 0.0;
        sampleNumber = 0;
    }

    public SineSource(int sr, double f, double p) {
        sampleRate = sr;
        frequency = f;
        phaseOffset = p;
        sampleNumber = 0;
    }

    public void setFrequency(double f) {
        newFrequency = f;
    }

    public double output() {
        double r;
        double position = (double) sampleNumber / sampleRate;
        double offset = (phaseOffset / 360) / frequency;

        r = Math.sin(2 * Math.PI * (position + offset) * frequency);

        sampleNumber++;
        if (sampleNumber == sampleRate) {
            sampleNumber = 0;
            if (newFrequency != null) {
                frequency = newFrequency;
                newFrequency = null;
            }
        }

        return r;
    }
}
