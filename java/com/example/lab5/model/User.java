package com.example.lab5.model;

public class User {
    int id;
    String name;
    String pass;
    String email;
    String date;

    public User() {
        super();
    }

    public User(String name, String email, String pass) {
        this.name = name;
        this.pass = pass;
        this.email = email;
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User(int id, String name, String pass, String email, String date) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.email = email;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
