package com.wildcodeschool.wildandwizard.controller;

import com.wildcodeschool.wildandwizard.entity.School;
import com.wildcodeschool.wildandwizard.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SchoolController {

    @Autowired
    private SchoolRepository schoolRepository;


    @GetMapping("/schools")
    public String getAll(Model model) {
        List<School> schools = schoolRepository.findAll();
        model.addAttribute("schools", schools);
        return "schools";
    }

    @GetMapping("/school")
    public String getSchool(Model model,
                            @RequestParam(required = false) Long id) {

        School school = new School();
        model.addAttribute("school", school);
        return "school";
    }

    @PostMapping("/school")
    public String postSchool(@ModelAttribute School school) {
        schoolRepository.save(school);
        return "redirect:/schools";
    }

    @GetMapping("/school/delete")
    public String deleteSchool(@RequestParam Long id) {
        schoolRepository.deleteById(id);
        return "redirect:/schools";
    }
}
