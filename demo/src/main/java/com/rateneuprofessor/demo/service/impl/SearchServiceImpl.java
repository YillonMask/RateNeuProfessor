package com.rateneuprofessor.demo.service.impl;

import com.rateneuprofessor.demo.entity.Course;
import com.rateneuprofessor.demo.entity.Professor;
import com.rateneuprofessor.demo.entity.Sort;
import com.rateneuprofessor.demo.entity.comparator.sortByCourseCode;
import com.rateneuprofessor.demo.entity.comparator.sortByProfessorName;
import com.rateneuprofessor.demo.repository.CourseRepository;
import com.rateneuprofessor.demo.repository.ProfessorRepository;
import com.rateneuprofessor.demo.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public List<Course> searchCourses(String keyword, Integer campusId, Sort sort) {
        // search courses in database
        Set<Course> courses = new HashSet<Course>();
        courses.addAll(courseRepository.searchCourseByName(keyword));
        courses.addAll(courseRepository.searchCourseByCode(keyword));
        List<Course> courseList = new ArrayList<>(courses);
        switch (sort){
            case CourseCode:
                Collections.sort(courseList, new sortByCourseCode());
                break;
            default:
                break;
        }
        if(campusId == null){
            return courseList;
        }else{
            List<Course> coursesByCampus = courseList.stream()
                    .filter(course -> course.getCampusId().equals(campusId))
                    .collect(Collectors.toList());
            return coursesByCampus;
        }
    }

    @Override
    public List<Professor> searchProfessors(String keyword, Integer campusId, Sort sort) {
        // search professors in database
        Set<Professor> professors = new HashSet<>();
        professors.addAll(professorRepository.searchProfessorByName(keyword));
        List<Professor> professorList = new ArrayList<>(professors);
        switch (sort){
            case ProfessorName:
                Collections.sort(professorList, new sortByProfessorName());
                break;
            default:
                break;
        }
        if(campusId == null){
            return professorList;
        }else{
            List<Professor> professorsByCampus = professorList.stream()
                    .filter(professor -> professor.getCampusId().equals(campusId))
                    .collect(Collectors.toList());
            return professorsByCampus;
        }
    }

}
