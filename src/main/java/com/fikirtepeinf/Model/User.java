package com.fikirtepeinf.Model;

public class User {
    private String tc;

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    private String password;

    public User(){

    };
    public User(String tc, String password){
        this.tc= tc;
        this.password = password;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
