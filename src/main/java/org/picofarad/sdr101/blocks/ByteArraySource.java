package org.picofarad.sdr101.blocks;

import java.util.Deque;
import java.util.ArrayDeque;

public class ByteArraySource implements SignalBlock {
    byte[] input;
    int highIndex;
    int lowIndex;

    public ByteArraySource(byte[] b, int h, int l) {
	input = b;
	highIndex = h;
	lowIndex = l;
    }

    public double output() {
	short s = (short) ((input[highIndex] << 8) | (input[lowIndex] & 0xFF));
	double d = s / 32768.0;

	return d;
    }
}
