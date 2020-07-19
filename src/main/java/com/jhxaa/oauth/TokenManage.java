package com.jhxaa.oauth;

import com.jhxaa.oauth.util.BeanUtil;
import com.jhxaa.oauth.util.RC4Util;

import java.util.Map;

public class TokenManage {


    public String createToken(String key, Jwt jwt) {
        String toMapString = BeanUtil.toMapString(jwt);
        try {
            return RC4Util.create(toMapString, key, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Jwt parseToken(String key, String token) {
        Jwt toBean = null;
        try {
            String parse = RC4Util.parse(token, key, 2);
            Map<String, Object> toMap = BeanUtil.stringToMap(parse);
            toBean = BeanUtil.toBean(toMap, Jwt.class);
        } catch (Throwable e) {
            e.printStackTrace();
            System.err.println(e + e.getMessage());
            throw new TokenException("parse token fail.");
        }
        if (toBean.getExpiration() <= System.currentTimeMillis()) {
            throw new ExpirationException("token time has expiration.");
        }
        return toBean;
    }
}

