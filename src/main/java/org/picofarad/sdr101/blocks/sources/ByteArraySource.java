/* sdr101-java
 * Simple software-defined radio for Java.
 *
 * (c) Karl-Martin Skontorp <kms@skontorp.net> ~ http://picofarad.org/
 * Licensed under the GNU GPL 2.0 or later.
 */

package org.picofarad.sdr101.blocks.sources;

import org.picofarad.sdr101.blocks.SignalBlock;

public class ByteArraySource implements SignalBlock {
    byte[] input;
    int highIndex;
    int lowIndex;

    public ByteArraySource(byte[] b, int h, int l) {
        input = b;
        highIndex = h;
        lowIndex = l;
    }

    public void setHighIndex(int i) {
        highIndex = i;
    }

    public void setLowIndex(int i) {
        lowIndex = i;
    }

    public double output() {
        short s = (short) ((input[highIndex] << 8) | (input[lowIndex] & 0xFF));
        double d = s / 32768.0;

        return d;
    }
}
