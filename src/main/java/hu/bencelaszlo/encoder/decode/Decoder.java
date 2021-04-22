package hu.bencelaszlo.encoder.decode;

import static hu.bencelaszlo.encoder.utility.Constants.ENCODED_VALUE_SEPARATOR;

/**
 * Decoder class is responsible for parsing and cd decoding ASCII codes from encoded values.
 * @author Bence László
 */
public class Decoder {

    /**
     * Parses bytes from string values.
     * @param strings the string to be parsed.
     * @return the parsed bytes.
     */
    private byte[] parseBytes(String[] strings) throws NumberFormatException {
        byte[] bytes = new byte[strings.length];

        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = Byte.parseByte(strings[i]);
        }

        return bytes;
    }

    /**
     * Decodes ASCII codes from the encoded byte values.
     * @param bytes the encoded byte values.
     * @return the decoded ASCII codes.
     */
    private byte[] decodeAsciiCodes(byte[] bytes) {
        byte[] asciiCodes = new byte[bytes.length];
        asciiCodes[0] = bytes[0];

        for (int i = 1; i < bytes.length; i++) {
            asciiCodes[i] = (byte) (asciiCodes[i-1] + bytes[i]);
        }

        return asciiCodes;
    }

    /**
     * Parses ASCII string from ASCII codes.
     * @param asciiCodes the ASCII codes.
     * @return the parsed ASCII string.
     */
    private String parseAsciiString(byte[] asciiCodes) {
        String parsedString = "";

        for (byte code : asciiCodes) {
            parsedString += (char)code;
        }

        return parsedString;
    }

    /**
     * Decodes encoded string.
     * @param encodedString the encoded string.
     * @return the decoded string.
     */
    public String decode(String encodedString) {
        String decodedString = "";

        if (encodedString.length() >= 1) {
            String[] values = encodedString.split(ENCODED_VALUE_SEPARATOR);
            byte[] numericValues = parseBytes(values);
            byte[] asciiCodes = decodeAsciiCodes(numericValues);
            decodedString = parseAsciiString(asciiCodes);
        }

        return decodedString;
    }

}
