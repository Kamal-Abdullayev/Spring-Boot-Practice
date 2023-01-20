package com.ltp.gradesubmission;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GradeController {

    List<Grade> gradeList = new ArrayList<>();
    @GetMapping("/")
    public String gradeForm(Model model, String id) {
        int index = getGradeIndex(id);
        model.addAttribute("grade", index == Constants.NOT_FOUND ? new Grade() : gradeList.get(index));
        return "form";
    }

    @PostMapping("/addGrade")
    public String addGrade(Grade grade) {
        int index = getGradeIndex(grade.getId());
        if (index == Constants.NOT_FOUND) {
            gradeList.add(grade);
        } else {
            gradeList.set(index, grade);
        }
        return "redirect:grades";
    }
    @GetMapping("/grades")
    public String getGrades(Model model) {
        System.out.println(gradeList);
        model.addAttribute("gradeList", gradeList);
        return "grades";
    }

    public Integer getGradeIndex(String id) {
        for (int i=0; i<gradeList.size(); i++) {
            if (gradeList.get(i).getId().equals(id)) return i;
        }
        return Constants.NOT_FOUND;
    }


}
