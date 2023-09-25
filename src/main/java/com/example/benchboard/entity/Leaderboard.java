package com.example.benchboard.entity;

import javax.persistence.*;

@Entity
@Table(name = "totals")
public class Leaderboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "squat")
    private float squat;

    @Column(name = "bench")
    private float bench;

    @Column(name = "deadlift")
    private float deadlift;

    public Leaderboard() {
        // empty constructor required by JPA
    }

    public Leaderboard(Long id, String username, float squat, float bench, float deadlift) {
        this.id = id;
        this.username = username;
        this.squat = squat;
        this.bench = bench;
        this.deadlift = deadlift;
    }


    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public Float getSquat(){
        return squat;
    }

    public void setSquat(Float squat){
        this.squat = squat;
    }

    public Float getBench(){
        return bench;
    }

    public void setBench(Float bench){
        this.bench = bench;
    }

    public Float getDeadlift(){
        return deadlift;
    }

    public void setDeadlift(Float deadlift){
        this.deadlift = deadlift;
    }
}
