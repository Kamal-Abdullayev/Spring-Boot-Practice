package com.ltp.gradesubmission;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

public class Grade {
    @NotBlank(message = "The name field couldn't be null!")
    private String name;
    @NotBlank(message = "Subject shoudn't be null!")
    private String subject;
    @Score(message = "Score must be a letter grade!")
    private String score;
    private String id;


    public Grade() {
        this.id = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
