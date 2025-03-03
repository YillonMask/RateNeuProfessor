package com.rateneuprofessor.demo.serviceTest;

import com.rateneuprofessor.demo.entity.Course;
import com.rateneuprofessor.demo.entity.Professor;
import com.rateneuprofessor.demo.entity.Sort;
import com.rateneuprofessor.demo.service.SearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SearchServiceImplTest {
    @Autowired
    SearchService searchService;

    @Test
    public void testSearchCourses() {
        List<Course> courses = searchService.searchCourses("Machine Learning",null, Sort.Default);
        assertEquals(1, courses.size());
        List<Course> courses2 = searchService.searchCourses("Privacy Security and Usability",null, Sort.Default);
        assertEquals(1, courses2.size());
    }

    @Test
    public void testSearchProfessors() {
        List<Professor> professors = searchService.searchProfessors("Mark Miller",null, Sort.Default);
        assertEquals(1, professors.size());
        List<Professor> professors2 = searchService.searchProfessors("Prof Not Found",null, Sort.Default);
        assertEquals(0, professors2.size());
    }
}
