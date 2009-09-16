/* sdr101-java
 * Simple software-defined radio for Java.
 *
 * (c) Karl-Martin Skontorp <kms@skontorp.net> ~ http://picofarad.org/
 * Licensed under the GNU GPL 2.0 or later.
 */

package org.picofarad.sdr101.blocks;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public abstract class FilterFactory {
    protected static List<Double> loadCoefficientsFromFile(String f)
            throws IOException, FileNotFoundException {
        BufferedReader br = openFile(f);

        List<Double> coefficients = new ArrayList<Double>();

        String line;
        while ((line = br.readLine()) != null) {
            processLine(coefficients, line);
        }

        return coefficients;
    }

	private static void processLine(List<Double> coefficients, String line)
			throws IOException {
		try {
		    double d = Double.valueOf(line.trim());
		    coefficients.add(d);
		} catch (NumberFormatException e) {
		    throw new IOException(e);
		}
	}

	private static BufferedReader openFile(String f)
			throws FileNotFoundException {
		InputStream is = FilterFactory.class.getResourceAsStream(f);
        if (is == null) {
            throw new FileNotFoundException();
        }
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
		return br;
	}

    public static FirFilter loadFirFromFile(String f) throws IOException {
        List<Double> c = loadCoefficientsFromFile(f);
        FirFilter ff = new FirFilter(c);
        return ff;
    }
}
