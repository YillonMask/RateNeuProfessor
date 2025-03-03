package com.rateneuprofessor.demo.repository;

import com.rateneuprofessor.demo.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
/**
 * Repository interface for Course repository.
 */
@Mapper
public interface CourseRepository {
    /**
     * add a course to the database
     *
     * @param courseName
     * @param professorId
     * @param courseCode
     * @param campusId
     *
     */
    @Select("INSERT INTO courses (course_name, professor_id, course_code, campus_id) VALUES (#{courseName}, #{professorId}, #{courseCode}, #{campusId})")
    void addCourse(String courseName, Integer professorId, String courseCode, Integer campusId);

    /**
     * Search course by course name
     * @param name
     * @return List of courses
     */
    @Select("SELECT c.course_id, c.course_name, c.course_code, " +
            "c.professor_id, p.name as professorName, c.campus_id, " +
            "COALESCE(AVG(r.rating), 0) as rating " +
            "FROM courses c " +
            "LEFT JOIN professors p ON c.professor_id = p.professor_id " +
            "LEFT JOIN reviews r ON c.course_id = r.course_id " +
            "WHERE c.course_name LIKE CONCAT('%', #{name}, '%') " +
            "GROUP BY c.course_id, c.course_name, c.course_code, c.professor_id, p.name, c.campus_id")
    List<Course> searchCourseByName(@Param("name") String name);

    /**
     * Search course by course code
     * @param code
     * @return List of courses
     */
    @Select("SELECT c.course_id, c.course_name, c.course_code, " +
            "c.professor_id, p.name as professorName, c.campus_id, " +
            "COALESCE(AVG(r.rating), 0) as rating " +
            "FROM courses c " +
            "LEFT JOIN professors p ON c.professor_id = p.professor_id " +
            "LEFT JOIN reviews r ON c.course_id = r.course_id " +
            "WHERE c.course_code LIKE CONCAT('%', #{code}, '%') " +
            "GROUP BY c.course_id, c.course_name, c.course_code, c.professor_id, p.name, c.campus_id")
    List<Course> searchCourseByCode(@Param("code") String code);

    /**
     * Get all courses
     * @return List of all courses
     */
    @Update("UPDATE courses " +
            "SET rating = COALESCE((SELECT AVG(rating) FROM reviews WHERE course_id = #{courseId}), 0) " +
            "WHERE course_id = #{courseId}")
    void updateRating(@Param("courseId") Integer courseId);

    /**
     * delete a course by courseId
     * @param courseId
     */
    @Select("DELETE FROM courses WHERE course_id = #{courseId}")
    void deleteCourse(Integer courseId);
}
