package com.jhxaa.oauth;

public class Jwt implements BuildSignInfo {
    String id;

    String issuedPeople;

    String recipient;

    String subject;

    String content;

    long issuedDate;

    long expiration;

    String signType;

    public Jwt() {

    }

    public static BuildSignInfo createBuildSignInfo() {
        return new Jwt();
    }

    public String getSignType() {
        return signType;
    }

    public String getId() {
        return id;
    }

    public String getIssuedPeople() {
        return issuedPeople;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public Object getContent() {
        return content;
    }

    public long getIssuedDate() {
        return issuedDate;
    }

    public long getExpiration() {
        return expiration;
    }


    @Override
    public BuildSignInfo setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public BuildSignInfo setIssuedPeople(String issuedPeople) {
        this.issuedPeople = issuedPeople;
        return this;
    }

    @Override
    public BuildSignInfo setRecipient(String recipient) {
        this.recipient = recipient;
        return this;
    }

    @Override
    public BuildSignInfo setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    @Override
    public BuildSignInfo setContent(String content) {
        this.content = content;
        return this;
    }

    @Override
    public BuildSignInfo setIssuedDate(long issuedDate) {
        this.issuedDate = issuedDate;
        return this;
    }

    @Override
    public BuildSignInfo setExpiration(long expiration) {
        this.expiration = expiration;
        return this;
    }

    @Override
    public BuildSignInfo setSignType(String signType) {
        this.signType = signType;
        return this;
    }

    @Override
    public String buildToken(String key) {
        return new TokenManage().createToken(key, this);
    }

    @Override
    public BuildSignInfo parseToken(String key, String token) {
        return new TokenManage().parseToken(key, token);
    }


}
