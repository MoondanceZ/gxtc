package com.rk.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Qin_Yikai on 2018-09-16.
 */
public class UserInfo implements Serializable {
    public UserInfo() {
    }

    public UserInfo(long id, String account, String name, String password, Date createDate, Date lastLoginDate) {
        this.id = id;
        this.account = account;
        this.name = name;
        this.password = password;
        this.createDate = createDate;
        this.lastLoginDate = lastLoginDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    private long id;
    private String account;
    private String name;
    private String password;
    private Date createDate;
    private Date lastLoginDate;

}
