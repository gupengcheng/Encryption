package com.gupengcheng.httpencryption;

import android.text.TextUtils;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by pcgu on 16-1-22.
 */
public class Encryption {
    private String mOffSet = "0123456789123456";
    private String mSecretKey = "0123456789abcdef";

    private IvParameterSpec mIvParameterSpec;
    private SecretKeySpec mSecretKeySpec;
    private Cipher mCipher;

    private static Encryption mInstance;

    public static Encryption getInstance() {
        if (null == mInstance) {
            mInstance = new Encryption();
        }
        return mInstance;
    }

    public Encryption() {
        //偏移量
        mIvParameterSpec = new IvParameterSpec(mOffSet.getBytes());
        //生成密钥
        mSecretKeySpec = new SecretKeySpec(mSecretKey.getBytes(), "AES");
        try {
            mCipher = Cipher.getInstance("AES/CBC/NoPading");
        } catch (NoSuchAlgorithmException exception) {
            exception.printStackTrace();
        } catch (NoSuchPaddingException exception) {
            exception.printStackTrace();
        }
    }

    public byte[] encrypt(String text) {
        if (TextUtils.isEmpty(text)) {
            return null;
        }
        byte[] encrypted = null;
        try {
            mCipher.init(Cipher.ENCRYPT_MODE, mSecretKeySpec, mIvParameterSpec);
            encrypted = mCipher.doFinal(EncryptionUtils.padString(text).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encrypted;
    }

    public byte[] decrypt(String text) {
        if (TextUtils.isEmpty(text)) {
            return null;
        }
        byte[] decrypted = null;
        try {
            mCipher.init(Cipher.DECRYPT_MODE, mSecretKeySpec, mIvParameterSpec);
            decrypted = mCipher.doFinal(EncryptionUtils.hex2Bytes(text));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decrypted;
    }

}
