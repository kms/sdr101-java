/* sdr101-java
 * Simple software-defined radio for Java.
 *
 * (c) Karl-Martin Skontorp <kms@skontorp.net> ~ http://picofarad.org/
 * Licensed under the GNU GPL 2.0 or later.
 */

package org.picofarad.sdr101.blocks;

public class CumulativeAverageFilter implements SignalBlock {
    private SignalBlock input;
    private int length;
    private double sum;

    public CumulativeAverageFilter(SignalBlock s, int l) {
	input = s;
	length = l;
	sum = 0.0;
    }

    public double output() {
	sum -= sum / length;
	sum += input.output() / length;

	return sum;
    }
}
