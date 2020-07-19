package com.jhxaa.oauth.test;

import com.jhxaa.oauth.BuildSignInfo;
import com.jhxaa.oauth.Jwt;

import java.util.Date;
import java.util.UUID;

public class Test {
    public static void main(String[] args) {
        String buildToken = Jwt.createBuildSignInfo()
                .setId(UUID.randomUUID().toString())
                .setIssuedPeople("jhxaa.com")
                .setIssuedDate(new Date().getTime())
                .setRecipient("xiaoan")
                .setSubject("token")
//                .setContent("内容")
//                .setSignType(TokenType.RC4.name())
                .setExpiration(new Date(System.currentTimeMillis() + 300000).getTime())
                .buildToken("pwd");
        System.out.println(buildToken);

        BuildSignInfo parseToken = Jwt.createBuildSignInfo().parseToken("pwd", buildToken);
    }

}
