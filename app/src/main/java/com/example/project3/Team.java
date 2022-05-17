package com.example.project3;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "team", foreignKeys = @ForeignKey(entity = User.class, parentColumns = "user_id", childColumns = "user_id", onDelete = CASCADE, onUpdate = CASCADE))
public class Team {
    @PrimaryKey(autoGenerate = true)
    private int team_id;

    @ColumnInfo(name = "user_id")
    private int user_id;

    public Team() {
        // empty
    }

    public Team(int teamId, int userId) {
        this.team_id = teamId;
        this.user_id = userId;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
