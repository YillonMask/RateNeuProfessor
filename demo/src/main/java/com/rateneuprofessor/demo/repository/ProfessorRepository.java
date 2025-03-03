package com.rateneuprofessor.demo.repository;

import com.rateneuprofessor.demo.entity.Professor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Repository interface for Professor repository.
 */
@Mapper
public interface ProfessorRepository {

    /**
     * add professor to database
     * @param name the name of the professor
     * @param campusId the id of the campus where the professor works
     */
    @Select("INSERT INTO professors (name, campus_id) VALUES (#{name}, #{campusId})")
    void addProfessor(String name, Integer campusId);

    /**
     * search for professor by name
     * @param name the name of the professor to search for
     * @return
     */
    @Select("SELECT p.professor_id, p.name, p.campus_id, " +
            "COALESCE(AVG(r.rating), 0) as rating " +
            "FROM professors p " +
            "LEFT JOIN reviews r ON p.professor_id = r.professor_id " +
            "WHERE p.name LIKE CONCAT('%', #{name}, '%') " +
            "GROUP BY p.professor_id, p.name, p.campus_id")
    List<Professor> searchProfessorByName(@Param("name") String name);

    /**
     * Get the professor by course id.
     * @param courseId the id of the professor to search for
     */
    @Select("SELECT professor_id FROM courses WHERE course_id = #{courseId}")
    Integer getProfessorIdByCourseId(@Param("courseId") Integer courseId);

    /**
     * update the rating of thr professor of given professorId
     * @param professorId
     */
    @Update("UPDATE professors " +
            "SET rating = COALESCE((SELECT AVG(rating) FROM reviews WHERE professor_id = #{professorId}), 0) " +
            "WHERE professor_id = #{professorId}")
    void updateRating(@Param("professorId") Integer professorId);

    /**
     * delete a professor by professorId
     * @param professorId
     */
    @Select("DELETE FROM professors WHERE professor_id = #{professorId}")
    void deleteProfessor(Integer professorId);
}
