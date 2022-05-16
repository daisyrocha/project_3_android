package com.example.project3;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * CRUD operations - create, read, update, delete
 * The Data Access Object AKA DAO allows us to add users
 * Get the number of users in our database
 * Get a list of all users in our database
 * And deleting users
 */
@Dao
public interface UserDao {

    @Insert
    void addUser(User user);

    // SQL query
    @Query("SELECT COUNT(*) FROM user")
    int count();

    @Query("SELECT * FROM user")
    List<User> getAllUsers();

    @Delete
    void delete(User user);

    @Query("SELECT * FROM user WHERE username = :eUsername")
    User getUserByUsername(String eUsername);

    @Query("SELECT * FROM user WHERE user_id = :uId")
    User getUserById(int uId);

    @Query("SELECT user_id FROM user WHERE username = :eUsername")
    int getUserId(String eUsername);

}
