package com.rateneuprofessor.demo.comparatorTest;

import com.rateneuprofessor.demo.entity.Course;
import com.rateneuprofessor.demo.entity.comparator.sortByCourseCode;
import com.rateneuprofessor.demo.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class sortByCourseCodeTest {
    @Autowired
    CourseRepository courseRepository;
    List<Course> courses = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        courses.add(new Course(1, "Test one", "789", 1,"Test",1,3.00));
        courses.add(new Course(2, "Test two", "123", 1,"Test",1,3.00));
        courses.add(new Course(3, "Test three", "456", 1,"Test",1,3.00));
    }
    @Test
    public void testSortByCourseCode() {
        courses.sort(new sortByCourseCode());
        String expected = "123";
        assertEquals(expected, courses.get(0).getCourseCode());
        expected = "456";
        assertEquals(expected, courses.get(1).getCourseCode());
        expected = "789";
        assertEquals(expected, courses.get(2).getCourseCode());
    }
}
