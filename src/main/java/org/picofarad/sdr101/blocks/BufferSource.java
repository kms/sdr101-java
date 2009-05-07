/* sdr101-java
 * Simple software-defined radio for Java.
 *
 * (c) Karl-Martin Skontorp <kms@skontorp.net> ~ http://picofarad.org/
 * Licensed under the GNU GPL 2.0 or later.
 */

package org.picofarad.sdr101.blocks;

import java.util.ArrayDeque;
import java.util.Deque;

public class BufferSource implements SignalBlock {
    private Deque<Double> buffer;

    public BufferSource() {
        buffer = new ArrayDeque<Double>();
    }

    public void buffer(double d) {
        buffer.offer(d);
    }

    public double output() {
        if (buffer.isEmpty()) {
            return 0.0;
        } else {
            return buffer.remove();
        }
    }
}
