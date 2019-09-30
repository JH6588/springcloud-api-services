package com.hd.utils;


import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

public class EncryptUtils {

    public static String getMD5(String str) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes());
        byte[] digest = md.digest();

        return DatatypeConverter
                .printHexBinary(digest).toLowerCase();

    }

    // personal case ,use for encrypt string
    private static String decodeBase64ToArryStr(String encodedString) {


        ArrayList<String> arr = new ArrayList<>();
        for (Byte i : Base64.getDecoder().decode(encodedString)) {
            arr.add((char) (i & 0xFF) + "");
        }
        return String.join("", arr);

    }

    public static String encodeBase64(String str) {

        return Base64.getEncoder().encodeToString(str.getBytes());

    }


}
