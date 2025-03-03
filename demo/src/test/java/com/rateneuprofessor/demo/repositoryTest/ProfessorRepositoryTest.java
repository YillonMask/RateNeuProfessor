package com.rateneuprofessor.demo.repositoryTest;

import com.rateneuprofessor.demo.entity.Course;
import com.rateneuprofessor.demo.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProfessorRepositoryTest {
    @Autowired
    CourseRepository courseRepository;
    @Test
    public void testAddCourse() {
        courseRepository.addCourse("Test Add Course 1", 1, "Test Code 1", 1);
        courseRepository.addCourse("Test Add Course 2", 3, "Test Code 2", 2);
        List<Course> courseTestOne = courseRepository.searchCourseByName("Test Add Course 1");
        assertEquals("Test Add Course 1", courseTestOne.get(0).getCourseName());
        List<Course> courseTestTwo = courseRepository.searchCourseByName("Test Add Course 2");
        assertEquals("Test Add Course 2", courseTestTwo.get(0).getCourseName());
        courseRepository.deleteCourse(courseTestOne.get(0).getCourseId());
        courseRepository.deleteCourse(courseTestTwo.get(0).getCourseId());
    }

    @Test
    public void testSearchCourseByName() {
        List<Course> courses = courseRepository.searchCourseByName("Discrete Structures");
        assertEquals(15, courses.size());
        List<Course> courses2 = courseRepository.searchCourseByName("Course Not Found");
        assertEquals(0, courses2.size());
    }

    @Test
    public void testSearchCourseByCode() {
        List<Course> courses = courseRepository.searchCourseByCode("5002");
        assertEquals(15, courses.size());
        List<Course> courses2 = courseRepository.searchCourseByCode("Course Not Found");
        assertEquals(0, courses2.size());
    }

    @Test
    public void testUpdateRating() {
        courseRepository.updateRating(21);
        List<Course> courses = courseRepository.searchCourseByName("Mobile Application Development");
        assertEquals(4.00, (double)courses.get(0).getRating(), 0.01);
        courseRepository.updateRating(58);
        List<Course> courses2 = courseRepository.searchCourseByName("Building Scalable Distributed Systems");
        assertEquals(0.00, (double)courses2.get(0).getRating(), 0.01);
    }

    @Test
    public void testDeleteCourse() {
        courseRepository.addCourse("Test Delete Course", 1, "Test Code", 1);
        List<Course> courses = courseRepository.searchCourseByName("Test Delete Course");
        assertEquals(1, courses.size());
        courseRepository.deleteCourse(courses.get(0).getCourseId());
        List<Course> courseAfterDelete = courseRepository.searchCourseByName("Test Delete Course");
        assertEquals(0, courseAfterDelete.size());
    }
}
