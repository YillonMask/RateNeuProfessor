package com.rateneuprofessor.demo.service.impl;

import com.rateneuprofessor.demo.entity.Course;
import com.rateneuprofessor.demo.entity.Professor;
import com.rateneuprofessor.demo.repository.CourseRepository;
import com.rateneuprofessor.demo.repository.ProfessorRepository;
import com.rateneuprofessor.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository ;
    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public void addCourse(String courseName, String name, String courseCode, Integer campusId) {
        // add course to database
        List<Professor> professors = professorRepository.searchProfessorByName(name);
        Integer professorId = professors.get(0).getProfessorId();
        courseRepository.addCourse(courseName, professorId, courseCode, campusId);
    }

    @Override
    public List<Course> searchCourseByName(String name) {
        // search course by name
        return courseRepository.searchCourseByName(name);
    }

    @Override
    public List<Course> searchCourseByCode(String code) {
        // search course by code
        return courseRepository.searchCourseByCode(code);
    }

    @Override
    public void deleteCourse(Integer courseId) {
        // delete course from database
        courseRepository.deleteCourse(courseId);
    }
}
