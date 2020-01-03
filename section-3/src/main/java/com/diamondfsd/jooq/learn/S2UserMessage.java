package com.diamondfsd.jooq.learn;

import com.diamondfsd.jooq.learn.codegen.tables.pojos.S2UserMessagePojo;

/**
 * 用户消息关联表
 * @author Diamond
 */
public class S2UserMessage extends S2UserMessagePojo {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
