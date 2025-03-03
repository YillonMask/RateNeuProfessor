package com.rateneuprofessor.demo.controller;

import com.rateneuprofessor.demo.service.ProfessorService;
import com.rateneuprofessor.demo.service.impl.ProfessorServiceImpl;
import com.rateneuprofessor.demo.utils.GsonTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @PostMapping("/professor")
    public String addProfessor(
            @RequestParam("name") String name,
            @RequestParam("campus_id") Integer campusId
    ) {
        professorService.addProfessor(name, campusId);
        return GsonTools.success("Professor added successfully");
    }
}