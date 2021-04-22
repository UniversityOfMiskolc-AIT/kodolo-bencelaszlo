package hu.bencelaszlo.encoder.encode;

import java.nio.charset.StandardCharsets;

import static hu.bencelaszlo.encoder.utility.Constants.ENCODED_VALUE_SEPARATOR;

/**
 * Encoder class is responsible for encoding ASCII values.
 * @author Bence László
 */
public class Encoder {

    /**
     * Gets ASCII codes from an input string.
     * @param inputString the input string.
     * @return the parsed ASCII string.
     */
    private byte[] parseAsciiCodes(String inputString) {
        return inputString.getBytes(StandardCharsets.US_ASCII);
    }

    /**
     * Encodes ASCII codes.
     * @param asciiCodes the ASCII codes.
     * @return the encoded ASCII codes.
     */
    private byte[] encodeAsciiCodes(byte[] asciiCodes) {
        byte[] encodedValues = new byte[asciiCodes.length];

        if (asciiCodes.length > 0) {
            encodedValues[0] = asciiCodes[0];

            for (int i = 1; i < asciiCodes.length; i++) {
                encodedValues[i] = (byte) (asciiCodes[i] - asciiCodes[i - 1]);
            }
        }

        return encodedValues;
    }

    /**
     * Encodes an ASCII string in the following way:
     * value_0,value2,...,_value_n where
     * value_0 is the ASCII code of the inputString's first character
     * value_j is calculated as the difference of the ASCII values of value_j and value_{j-1} (j = 1, ..., n)
     * @param inputString a string to be encoded.
     * @return the encoded strings.
     */
    public String encode(String inputString) {
        byte[] asciiCodes = parseAsciiCodes(inputString);
        byte[] encodedValues = encodeAsciiCodes(asciiCodes);
        return resultFormatter(encodedValues);
    }

    /**
     * Formats the given byte array input as value_0,value_1,...,value_n.
     * @param encodedResult the byte array input.
     * @return the formatted string.
     */
    private String resultFormatter(byte[] encodedResult) {
        StringBuilder resultString = new StringBuilder();

        if (encodedResult.length < 1) {
            return resultString.toString();
        } else {

            for (int i = 0; i < encodedResult.length; i++) {
                resultString.append(encodedResult[i]);

                if (encodedResult[(i)] != '-') {
                    resultString.append(ENCODED_VALUE_SEPARATOR);
                }
            }

            return resultString.substring(0, resultString.length() - 1);
        }
    }
}
