package com.rateneuprofessor.demo.controller;

import com.rateneuprofessor.demo.service.CourseService;
import com.rateneuprofessor.demo.service.impl.CourseServiceImpl;
import com.rateneuprofessor.demo.utils.GsonTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("/course")
    public String addCourse(
            @RequestParam("course_name") String courseName,
            @RequestParam("course_code") String courseCode,
            @RequestParam("professor_name") String professorName,
            @RequestParam("campus_id") Integer campusId
    ) {
        try {
            courseService.addCourse(courseName, professorName, courseCode, campusId);
            return GsonTools.success("Course added successfully");
        } catch (NullPointerException e) {
            return GsonTools.error("Professor not found");
        }
    }
}