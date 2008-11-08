package org.picofarad.sdr101;

import java.util.Map;
import java.util.HashMap;
import java.util.Deque;
import java.util.ArrayDeque;

public class Splitter {
    private SignalBlock source;
    private Map<SplitterOutput, Deque<Double>> outputs;

    public Splitter(SignalBlock s) {
	source = s;
	outputs = new HashMap<SplitterOutput, Deque<Double>>();
    }

    public void setSource(SignalBlock sb) {
	source = sb;
    }

    public SplitterOutput createOutput() {
	SplitterOutput so = new SplitterOutput(this);
	outputs.put(so, new ArrayDeque<Double>());

	return so;
    }

    private void fillAllBuffers() {
	double sample = source.out();

	for (Deque<Double> buffer : outputs.values()) {
	    buffer.offer(sample);
	}
    }

    public double out(SplitterOutput so) {
	Deque<Double> buffer = outputs.get(so);
	if (buffer == null) {
	    return 0.0;
	} else {
	    if (buffer.size() == 0) {
		fillAllBuffers();
	    }

	    return buffer.remove();
	}
    }
}
