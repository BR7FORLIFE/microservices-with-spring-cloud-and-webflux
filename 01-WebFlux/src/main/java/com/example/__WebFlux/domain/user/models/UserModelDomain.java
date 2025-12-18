package com.example.__WebFlux.domain.user.models;

import java.util.List;

public class UserModelDomain {
    private String id;
    private String username;
    private String email;
    private String password;
    private List<String> rols;

    public UserModelDomain() {
    }

    public UserModelDomain(String id, String username, String email, String password, List<String> rols) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.rols = rols;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRols() {
        return rols;
    }

    public void setRols(List<String> rols) {
        this.rols = rols;
    }
}
