package org.picofarad.sdr101;

import java.util.Deque;
import java.util.ArrayDeque;

public class BufferSource implements SignalBlock {
    private Deque<Double> buffer;

    public BufferSource() {
	buffer = new ArrayDeque<Double>();
    }

    public void buffer(double d) {
	buffer.offer(d);
    }

    public double out() {
	if (buffer.isEmpty()) {
	    return 0.0;
	} else {
	    return buffer.remove();
	}
    }
}