package com.example.project3;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "heroesTable")
public class Heroes {

    @PrimaryKey(autoGenerate = true)
    private int heroId;

    @ColumnInfo(name = "userId")
    private int userId;

    @ColumnInfo(name = "heroName")
    private String heroName;

    @ColumnInfo(name = "count")
    private int count;

    /**
     * Heroes Constructor
     */
    public Heroes(int userId, String heroName, int count) {
        this.userId = userId;
        this.heroName = heroName;
        this.count = count;
    }

    /**
     * Getters and Setters for the Heroes entity
     */
    public int getHeroId() {
        return heroId;
    }

    public void setHeroId(int heroId) {
        this.heroId = heroId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
