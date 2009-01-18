/* sdr101-java
 * Simple software-defined radio for Java.
 *
 * (c) Karl-Martin Skontorp <kms@skontorp.net> ~ http://picofarad.org/
 * Licensed under the GNU GPL 2.0 or later.
 */

package org.picofarad.sdr101.blocks;

public class EnvelopeDetector implements SignalBlock {
    private SignalBlock input;

    public EnvelopeDetector(SignalBlock s, int l) {
	FullWaveRectifier fwr = new FullWaveRectifier(s);
	input = new CumulativeAverageFilter(fwr, l);
    }

    public double output() {
	return input.output();
    }
}
