package com.ltp.gradesubmission.service;

import java.util.List;

import com.ltp.gradesubmission.Constants;
import com.ltp.gradesubmission.Grade;
import com.ltp.gradesubmission.repository.GradeReposiory;

public class GradeService {
    
    GradeReposiory gradeReposiory = new GradeReposiory();

    public Grade getGrade(int index) {
        return gradeReposiory.getGrade(index);
    }

    public void addGrade(Grade grade) {
        gradeReposiory.addGrade(grade);
    }

    public void setGrade(Grade grade, int index) {
        gradeReposiory.setGrade(grade, index);
    }

    public List<Grade> getGrades() {
        return gradeReposiory.getGrades();
    }

    public int getGradeIndex(String id) {
        for (int i = 0; i < getGrades().size(); i++) {
            if (getGrades().get(i).getId().equals(id)) return i;
        }
        return Constants.NOT_FOUND;
    }

    public Grade getGradeById(String id) {
        int index = getGradeIndex(id);
        return index == Constants.NOT_FOUND ? new Grade() : getGrade(index);
    }

    public void submitGrade(Grade grade) {
        int index = getGradeIndex(grade.getId());
        if (index == Constants.NOT_FOUND) {
            addGrade(grade);
        } else {
            setGrade(grade, index);
        }
    }
}
