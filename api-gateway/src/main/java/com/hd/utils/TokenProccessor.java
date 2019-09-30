/**
 * 生成Token的工具类：
 */
package com.hd.utils;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.UUID;


/**
 * 生成Token的工具类  
 *
 */
public class TokenProccessor {

    private TokenProccessor() {
    }

    ;
    private static final TokenProccessor instance = new TokenProccessor();

    public static TokenProccessor getInstance() {
        return instance;
    }

    /**
     * 生成Token  
     * @return
     */
    public String getToken() {
        String token = (System.currentTimeMillis() + new Random().nextInt(999999999)) + "";
        return Hashing.sha256().hashString(token, StandardCharsets.UTF_8).toString();
    }

    public String getUniKey() {
        return UUID.randomUUID().toString();

    }
}  
