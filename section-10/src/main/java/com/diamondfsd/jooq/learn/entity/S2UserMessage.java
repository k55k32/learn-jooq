package com.diamondfsd.jooq.learn.entity;


import com.diamondfsd.jooq.learn.jooq.tables.pojos.S2UserMessagePojo;


public class S2UserMessage extends S2UserMessagePojo {
    String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
