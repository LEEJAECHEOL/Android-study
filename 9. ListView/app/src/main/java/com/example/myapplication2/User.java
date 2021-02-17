package com.example.myapplication2;

import java.io.Serializable;
// Serializable 를 붙이면 직렬화해서 알아서 역직렬화 해줌.
public class User implements Serializable {
    private int id;
    private String username;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
