package com.example.project3;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * CRUD operations - create, read, update, delete
 * The Data Access Object AKA DAO allows us to add heroes
 * Get the number of heroes in our database
 * Get a list of all heroes in our database
 * And deleting heroes
 */
@Dao
public interface HeroesDao {

    @Insert
    void addHero(Heroes hero);

    @Query("SELECT COUNT(*) FROM heroesTable")
    int count();

    @Query("SELECT * FROM heroesTable")
    List<Heroes> getAll();

    @Delete
    void delete(Heroes hero);

    @Query("SELECT * FROM heroesTable WHERE heroName = :eHeroName")
    User getUserByUsername(String eHeroName);
}
