package com.pojo;

import java.io.Serializable;

public class Users implements Serializable {
    public final static long serialVersionUID = 100L;
    private String u_id;
    private String u_name;
    private String u_pwd;
    private String u_priority;
    private Double u_account;

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_pwd() {
        return u_pwd;
    }

    public void setU_pwd(String u_pwd) {
        this.u_pwd = u_pwd;
    }

    public String getU_priority() {
        return u_priority;
    }

    public void setU_priority(String u_priority) {
        this.u_priority = u_priority;
    }

    public Double getU_account() {
        return u_account;
    }

    public void setU_account(double u_account) {
        this.u_account = u_account;
    }
}
