package com.rateneuprofessor.demo.repository;

import com.rateneuprofessor.demo.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository interface for Comment repository.
 */
@Mapper
public interface CommentRepository {
    /**
     * Add a comment to a course.
     *
     * @param courseId    The course id.
     * @param professorId The professor id.
     * @param rating      The rating.
     * @param comment     The content of the comment.
     * @param datePosted  The date the comment was posted.
     */
    @Select("INSERT INTO reviews (course_id, professor_id, rating, comment, date_posted) VALUES (#{courseId}, #{professorId}, #{rating}, #{comment}, #{datePosted})")
    void addComment(Integer courseId,Integer professorId, Integer rating,String comment, LocalDateTime datePosted);

    /**
     * Get all comments.
     *
     * @param professorId The professor id.
     * @return List of all comments.
     */
    @Select("SELECT r.review_id, r.course_id, c.course_name, c.course_code, " +
            "r.professor_id, p.name as professorName, c.campus_id, " +
            "r.rating, r.comment, r.date_posted as datePosted " +
            "FROM reviews r " +
            "JOIN courses c ON r.course_id = c.course_id " +
            "JOIN professors p ON r.professor_id = p.professor_id " +
            "WHERE r.professor_id = #{professorId}")
    List<Comment> getCommentsByProfessorId(@Param("professorId") Integer professorId);

    /**
     * Get all comments.
     *
     * @param courseId The course id.
     * @return List of all comments.
     */
    @Select("SELECT r.review_id, r.course_id, c.course_name, c.course_code, " +
            "r.professor_id, p.name as professorName, c.campus_id, " +
            "r.rating, r.comment, r.date_posted as datePosted " +
            "FROM reviews r " +
            "JOIN courses c ON r.course_id = c.course_id " +
            "JOIN professors p ON r.professor_id = p.professor_id " +
            "WHERE r.course_id = #{courseId}")
    List<Comment> getCommentsByCourseId(@Param("courseId") Integer courseId);

    /**
     * delete a comment by commentId
     * @param commentId
     */
    @Select("DELETE FROM reviews WHERE review_id = #{commentId}")
    void deleteComment(Integer commentId);
}
