package org.picofarad.sdr101.blocks;

import org.junit.Test;
import org.junit.Assert;

public class ByteArraySourceTest {
    @Test
    public void testOutput() { 
        byte[] b = new byte[4];
        ByteArraySource bas = new ByteArraySource(b, 0, 1);

        b[0] = (byte) 0x00;
        b[1] = (byte) 0x00;
        Assert.assertEquals(0.0, bas.output(), 0.001);

        b[0] = (byte) 0x80;
        b[1] = (byte) 0x00;
        Assert.assertEquals(-1.0, bas.output(), 0.001);

        b[0] = (byte) 0x7f;
        b[1] = (byte) 0xff;
        Assert.assertEquals(1.0, bas.output(), 0.001);

        b[2] = (byte) 0x80;
        b[3] = (byte) 0x00;
        bas = new ByteArraySource(b, 2, 3);
        Assert.assertEquals(-1.0, bas.output(), 0.001);
    }

    @Test
    public void testSetIndexes() { 
        byte[] b = new byte[4];
        ByteArraySource bas = new ByteArraySource(b, 0, 1);

        b[0] = (byte) 0x80;
        b[1] = (byte) 0x00;
        Assert.assertEquals(-1.0, bas.output(), 0.001);

        b[2] = (byte) 0x80;
        b[3] = (byte) 0x00;
        bas.setHighIndex(2);
        bas.setLowIndex(3);
        Assert.assertEquals(-1.0, bas.output(), 0.001);
    }
}
