package com.gupengcheng.httpencryption;

/**
 * Created by pcgu on 16-1-22.
 */
public class EncryptionUtils {
    public static String padString(String source) {
        char paddingChar = ' ';
        int size = 16;
        int x = source.length() % size;
        int padLength = size - x;

        for (int i = 0; i < padLength; i++) {
            source += paddingChar;
        }

        return source;
    }

    public static String byte2Hex(byte[] data) {
        if (data == null) {
            return null;
        }

        int len = data.length;
        String str = "";
        for (int i = 0; i < len; i++) {
            if ((data[i] & 0xFF) < 16) {
                str = str + "0" + Integer.toHexString(data[i] & 0xFF);
            } else {
                str = str + Integer.toHexString(data[i] & 0xFF);
            }
        }
        return str;
    }

    public static byte[] hex2Bytes(String data) {
        if (data == null) {
            return null;
        } else if (data.length() < 2) {
            return null;
        } else {
            int len = data.length() / 2;
            byte[] buffer = new byte[len];
            for (int i = 0; i < len; i++) {
                buffer[i] = (byte) Integer.parseInt(data.substring(i * 2, i * 2 + 2), 16);
            }
            return buffer;
        }
    }
}
