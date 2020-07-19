package com.jhxaa.oauth;

public interface BuildSignInfo extends Token {

    BuildSignInfo setId(String uid);

    BuildSignInfo setIssuedPeople(String issuedPeople);

    BuildSignInfo setRecipient(String recipient);

    BuildSignInfo setSubject(String subject);

    BuildSignInfo setContent(String content);

    BuildSignInfo setIssuedDate(long issuedDate);

    BuildSignInfo setExpiration(long expiration);

    BuildSignInfo setSignType(String signType);

}
