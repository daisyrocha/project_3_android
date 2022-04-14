package com.example.project3;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

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
}
