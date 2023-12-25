package com.as.test.utils;


import org.apache.commons.codec.binary.Base64;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class RsaEncrypt {

    public static String[] genKeyPair() throws NoSuchAlgorithmException {
        String[] s = new String[2];
        KeyPairGenerator rsaGen = KeyPairGenerator.getInstance("RSA");
        rsaGen.initialize(1024, new SecureRandom());
        KeyPair keyPair = rsaGen.genKeyPair();
        RSAPrivateKey aPrivate = (RSAPrivateKey) keyPair.getPrivate();
        RSAPublicKey aPublic = (RSAPublicKey) keyPair.getPublic();
        String pri = new String(Base64.encodeBase64(aPrivate.getEncoded()));
        String pub = new String(Base64.encodeBase64(aPublic.getEncoded()));
        s[0] = pri;
        s[1] = pub;

        return s;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String[] s = genKeyPair();
        System.out.println(s[0]);
        System.out.println(s[1]);
    }
}
