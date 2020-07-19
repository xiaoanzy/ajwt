package com.jhxaa.oauth;

public interface Token {

    String buildToken(String key);

    BuildSignInfo parseToken(String key, String token);
}
