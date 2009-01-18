/* sdr101-java
 * Simple software-defined radio for Java.
 *
 * (c) Karl-Martin Skontorp <kms@skontorp.net> ~ http://picofarad.org/
 * Licensed under the GNU GPL 2.0 or later.
 */

package org.picofarad.sdr101.blocks;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.List;
import java.util.ArrayList;

public abstract class FilterFactory {
    protected static List<Double> loadCoefficientsFromFile(String f) 
	throws IOException, FileNotFoundException {
	InputStream is = FilterFactory.class.getResourceAsStream(f);
	if (is == null) {
	    throw new FileNotFoundException();
	}
	InputStreamReader isr = new InputStreamReader(is);
	BufferedReader br = new BufferedReader(isr);

	List<Double> coefficients = new ArrayList<Double>();

	String line;
	while ((line = br.readLine()) != null) {
	    try {
		double d = Double.valueOf(line.trim());
		coefficients.add(d);
	    } catch (NumberFormatException e) {
		throw new IOException(e);
	    }
	}

	return coefficients;
    }

    public static FirFilter loadFirFromFile(String f) throws IOException {
	List<Double> c = loadCoefficientsFromFile(f);
	FirFilter ff = new FirFilter(c);
	return ff;
    }
}
