package com.spacez.model.entity;

import org.hibernate.annotations.NamedQuery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Time;
import java.time.LocalDate;

@Entity
@Table(name = "launch")
@NamedQuery(name = "Launch.findbyId", query = "SELECT l FROM Launch l WHERE l.id = :id")
@NamedQuery(name = "Launch.findAll", query = "SELECT l FROM Launch l")

public class Launch {
    @Id
    @Column(name = "\"Launch_ID\"", nullable = false)
    private Integer id;

    @Column(name = "\"Mission_Name\"", nullable = false, length = 50)
    private String missionName;

    @Column(name = "\"SpaceCraft_Model\"", nullable = false, length = 50)
    private String spacecraftModel;

    @Column(name = "\"Date\"", nullable = false)
    private LocalDate date;

    @Column(name = "\"Time\"", nullable = false)
    private Time time;

    @Column(name = "\"Description\"", nullable = true)
    private String description;

    @Column(name = "\"Is_Launch\"", nullable = true)
    private boolean isLaunched;

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSpacecraftModel() {
        return spacecraftModel;
    }

    public void setSpacecraftModel(String spacecraftModel) {
        this.spacecraftModel = spacecraftModel;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription(){ return description; }

    public void setDescription(String description){ this.description = description; }

    public boolean getIsLaunch(){ return isLaunched; }

    public void setIsLaunched(boolean isLaunched){ this.isLaunched = isLaunched; }

}