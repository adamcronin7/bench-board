package com.example.benchboard.entity;

import javax.validation.constraints.*;

public class Calculator {

    private Float weight;

    @Min(value = 1, message = "Reps must be at least 1")
    @Max(value = 12, message = "Reps must be at most 12")
    private Integer reps;

    @Min(value = 6, message = "RPE must be at least 6")
    @Max(value = 10, message = "RPE must be at most 10")
    private Integer rpe;

    public Float getWeight(){ return weight; }

    public void setWeight(Float weight){ this.weight = weight; }

    public Integer getReps(){
        return reps;
    }

    public void setReps(Integer reps){ this.reps = reps; }

    public Integer getRpe(){
        return rpe;
    }

    public void setRpe(Integer rpe){ this.rpe = rpe; }
}