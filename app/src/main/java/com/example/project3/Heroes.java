package com.example.project3;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "heroes", foreignKeys = @ForeignKey(entity = Team.class, parentColumns = "team_id", childColumns = "team_id", onDelete = CASCADE, onUpdate = CASCADE))
public class Heroes {

    @PrimaryKey(autoGenerate = true)
    private int hero_id;

    @ColumnInfo(name = "combat")
    private int combat;

    @ColumnInfo(name = "durability")
    private int durability;

    @ColumnInfo(name = "intelligence")
    private int intelligence;

    @ColumnInfo(name = "power")
    private int power;

    @ColumnInfo(name = "speed")
    private int speed;

    @ColumnInfo(name = "strength")
    private int strength;

    @ColumnInfo(name = "team_id")
    private int team_id;

    /**
     * Heroes Constructor
     */
    public Heroes() {
        // empty
    }

    public Heroes(int heroId, int combat, int durability, int intelligence, int power, int speed, int strength, int teamId) {
        this.hero_id = heroId;
        this.combat = combat;
        this.durability = durability;
        this.intelligence = intelligence;
        this.power = power;
        this.speed = speed;
        this.strength = strength;
        this.team_id = teamId;
    }

    /**
     * Getters and Setters
     */
    public int getHero_id() {
        return hero_id;
    }

    public void setHero_id(int hero_id) {
        this.hero_id = hero_id;
    }

    public int getCombat() {
        return combat;
    }

    public void setCombat(int combat) {
        this.combat = combat;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }
}
