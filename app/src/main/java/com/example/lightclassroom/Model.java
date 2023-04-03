package com.example.lightclassroom;

public class Model {
    private int id;
    private  String username;
    private String std;

    //constructor

    public Model(int id, String username){
        this.id = id;
        this.username = username;
        this.std = std;
    }

    //getter and setter method

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
}
