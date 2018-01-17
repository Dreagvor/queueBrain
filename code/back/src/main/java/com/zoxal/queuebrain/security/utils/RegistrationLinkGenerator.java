package com.zoxal.queuebrain.security.utils;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Random;

/**
 * Generates registration link
 *
 * @author Mike
 * @version 01/13/2018
 */
public class RegistrationLinkGenerator {
    private static final String UPPERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERS = UPPERS.toLowerCase(Locale.ROOT);
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "-";
    private static final String ALLOWED_SYMBOLS = UPPERS + LOWERS + DIGITS + SPECIAL;
    private static final int LENGTH = 20;

    private static final Random random = new SecureRandom();

    public static String generate() {
        char[] buf = new char[LENGTH];
        for (int i = 0; i < buf.length; ++i) {
            buf[i] = ALLOWED_SYMBOLS.charAt(random.nextInt(ALLOWED_SYMBOLS.length()));
        }
        return new String(buf);
    }
}
