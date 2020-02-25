package com.example.appchatfb.model;

public class User {
    String email,pass,name,anh;
    Integer isonline;

    public User(String email, String pass, String name, String anh, Integer isonline) {
        this.email = email;
        this.pass = pass;
        this.name = name;
        this.anh = anh;
        this.isonline = isonline;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

        public Integer getIsonline() {
        return isonline;
    }

    public void setIsonline(Integer isonline) {
        this.isonline = isonline;
    }
}
