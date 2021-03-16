package com.shop.util.common;

import java.util.Random;
import java.util.UUID;

public class UUIDGenerator {

    private static final byte[] lock = new byte[0];
    private static final String NUM_CHAR = "0123456789";
    private static int charLen = "0123456789".length();

    public UUIDGenerator() {
    }

    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }

    public static String[] getUUID(int number) {
        if (number < 1) {
            return null;
        } else {
            String[] ss = new String[number];

            for(int i = 0; i < number; ++i) {
                ss[i] = getUUID();
            }

            return ss;
        }
    }

    public static String getRandomNumber(int randomNumberDigit) {
        long seed = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        synchronized(lock) {
            Random random = new Random(seed);

            for(int i = 0; i < randomNumberDigit; ++i) {
                sb.append("0123456789".charAt(random.nextInt(charLen)));
            }

            return sb.toString();
        }
    }
}
