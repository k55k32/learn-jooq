package com.diamondfsd.jooq.learn;

import com.diamondfsd.jooq.learn.codegen.tables.interfaces.IS1User;

import java.sql.Timestamp;

public class S1UserPojoNoInterface {
    private Integer   id;
    private String    username;
    private String    email;
    private String    address;
    private Timestamp createTime;
    private Timestamp updateTime;

    public S1UserPojoNoInterface() {}

    public S1UserPojoNoInterface(
            Integer   id,
            String    username,
            String    email,
            String    address,
            Timestamp createTime,
            Timestamp updateTime
    ) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.address = address;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
