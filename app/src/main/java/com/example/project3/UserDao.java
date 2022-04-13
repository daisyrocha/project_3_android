package com.example.project3;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void addUser(User user);

    @Query("SELECT COUNT(*) FROM userTable")
    int count();

    @Query("SELECT * FROM userTable")
    List<User> getAll();

    @Delete
    void delete(User user);
}
