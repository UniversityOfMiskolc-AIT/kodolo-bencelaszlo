package hu.bencelaszlo.encoder.encode;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EncoderTests {
    private final Encoder encoder = new Encoder();

    @Test
    @DisplayName("Encode ASCII strings")
    public void encode() {
        assertEquals("68", encoder.encode("D"));
        assertEquals("65,2", encoder.encode("AC"));
        assertEquals("51,-1,-1,3", encoder.encode("3214"));
    }

    @Test
    @DisplayName("Encode empty string")
    public void encodeEmptyString() {
        assertEquals("", encoder.encode(""));
    }

    @Test
    @DisplayName("Encode not ASCII characters")
    public void encodeNotAsciiChars() {
        assertEquals("63,0,0", encoder.encode("ÉÁŐ"));
    }
}