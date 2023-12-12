package com.example.foodorderapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Insert
    void insertUser(UserTable userTable);

    @Query("SELECT EXISTS (SELECT * from UserTable where username=:username)")
    boolean is_token(String username);

    @Query("SELECT EXISTS (SELECT * from UserTable where username=:username AND password=:password)")
    boolean login(String username, String password);



}
