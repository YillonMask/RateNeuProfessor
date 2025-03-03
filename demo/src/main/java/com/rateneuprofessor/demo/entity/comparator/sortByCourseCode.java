package com.rateneuprofessor.demo.entity.comparator;

import com.rateneuprofessor.demo.entity.Course;

import java.util.Comparator;

/**
 * Comparator class to sort courses by course code.
 */
public class sortByCourseCode implements Comparator<Course> {

    @Override
    public int compare(Course o1, Course o2) {
        return o1.getCourseCode().compareTo(o2.getCourseCode());
    }
}
