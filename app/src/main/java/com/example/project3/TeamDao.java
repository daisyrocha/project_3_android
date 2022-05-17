package com.example.project3;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

public interface TeamDao {
    @Insert
    void createTeam(Team team);

    @Query("SELECT * FROM team")
    List<Team> getAllTeams();

    @Query("SELECT * FROM team WHERE user_id = :userId")
    List<Team> findTeamsByUserId(int userId);

    @Query("SELECT * FROM team WHERE team_id = :teamId")
    List<Team> findTeamsByTeamId(int teamId);

    @Delete
    void delete(Team team);
}
