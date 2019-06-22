package com.example.mora.myproject.com.database;

import org.litepal.crud.DataSupport;

public class user extends DataSupport{
    private String username;
    private String password;
    public String getUsername() {
        return username;
    }

    public user setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public user setPassword(String password) {
        this.password = password;
        return this;
    }


}
