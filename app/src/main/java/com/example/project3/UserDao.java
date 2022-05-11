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
    @Query("SELECT COUNT(*) FROM userTable")
    int count();

    @Query("SELECT * FROM userTable")
    List<User> getAllUsers();

    @Delete
    void delete(User user);

    @Query("SELECT * FROM userTable WHERE username = :eUsername")
    User getUserByUsername(String eUsername);

    @Query("SELECT * FROM userTable WHERE userId = :uId")
    User getUserById(int uId);

    @Query("SELECT userId FROM userTable WHERE username = :eUsername")
    int getUserId(String eUsername);

}
