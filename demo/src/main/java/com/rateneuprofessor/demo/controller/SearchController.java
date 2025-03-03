package com.rateneuprofessor.demo.controller;

import com.rateneuprofessor.demo.entity.Course;
import com.rateneuprofessor.demo.entity.Professor;
import com.rateneuprofessor.demo.entity.Sort;
import com.rateneuprofessor.demo.service.SearchService;
import com.rateneuprofessor.demo.service.impl.SearchServiceImpl;
import com.rateneuprofessor.demo.utils.GsonTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SearchController {
    @Autowired
    private SearchService searchService;

    @GetMapping("/search")
    public String search(
            @RequestParam("keyword") String keyword,
            @RequestParam("sort") Integer sort,
            @RequestParam(value = "campus_id", required = false) Integer campusId,
            @RequestParam(value = "no_course", required = false, defaultValue = "false") boolean noCourse,
            @RequestParam(value = "no_professor", required = false, defaultValue = "false") boolean noProfessor) {

        Map<String, Object> result = new HashMap<>();

        if (!noCourse && !noProfessor) {
            List<Course> courses = searchService.searchCourses(keyword, campusId, Sort.values()[sort]);
            List<Professor> professors = searchService.searchProfessors(keyword, campusId, Sort.values()[sort]);
            result.put("courses", courses);
            result.put("professors", professors);
            return GsonTools.success("search completed", result);
        } else if (!noCourse) {
            List<Course> courses = searchService.searchCourses(keyword, campusId, Sort.values()[sort]);
            return GsonTools.success("search completed", courses);
        } else if (!noProfessor) {
            List<Professor> professors = searchService.searchProfessors(keyword, campusId, Sort.values()[sort]);
            return GsonTools.success("search completed", professors);
        } else {
            return GsonTools.error("No search result");
        }
    }
}