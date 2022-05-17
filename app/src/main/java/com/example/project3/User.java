package com.example.project3;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * The name of the table in our database is userTable
 * This table consists the columns "username" and "password"
 */
@Entity(tableName = "user")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int user_id;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "password")
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * When creating a new user account we first need to call the post function
     * to add the user to our API, then add the user into RoomDB.
     * Why? Because we need to make sure we don't have repeated primary keys (user_id)
     */
    public User(int user_id, String user_name, String pwd) {
        this.user_id = user_id;
        this.username = user_name;
        this.password = pwd;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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