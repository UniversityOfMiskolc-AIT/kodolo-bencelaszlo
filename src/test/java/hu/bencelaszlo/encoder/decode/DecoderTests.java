package hu.bencelaszlo.encoder.decode;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class DecoderTests {
    private final Decoder decoder = new Decoder();

    @Test
    @DisplayName("Decodes ASCII characters")
    public void decode() {
        assertEquals("", decoder.decode(""));
        assertEquals("D", decoder.decode("68"));
        assertEquals("AC", decoder.decode("65,2"));
        assertEquals("3214", decoder.decode("51,-1,-1,3"));
    }

    @Test
    @DisplayName("Decodes not ASCII characters")
    public void decodeNotAscii() {
        assertThrows(NumberFormatException.class, () -> decoder.decode("512,-1,-1,3"));
        assertThrows(NumberFormatException.class, () -> decoder.decode("512113"));
        assertThrows(NullPointerException.class, () -> decoder.decode(null));
    }
}
