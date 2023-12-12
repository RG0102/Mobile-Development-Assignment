package com.example.foodorderapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "usertable")
public class UserTable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String username;  //Username
    private String password;  //Password

    //Constructor
    public UserTable(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    //Getter
    public int getId() {
        return id;
    }

    //Setter
    public void setId(int id) {
        this.id = id;
    }

    //Getter
    public String getUsername() {
        return username;
    }

    //Setter
    public void setUsername(String username) {
        this.username = username;
    }

    //Getter
    public String getPassword() {
        return password;
    }

    //Setter
    public void setPassword(String password) {
        this.username = username;
    }

}
