package com.rateneuprofessor.demo.service.impl;

import com.rateneuprofessor.demo.entity.Comment;
import com.rateneuprofessor.demo.repository.CommentRepository;
import com.rateneuprofessor.demo.repository.CourseRepository;
import com.rateneuprofessor.demo.repository.ProfessorRepository;
import com.rateneuprofessor.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void addComment(Integer courseId, Integer rating, String content) {
        Integer professorId = professorRepository.getProfessorIdByCourseId(courseId);
        commentRepository.addComment(courseId, professorId, rating, content, LocalDateTime.now());
        professorRepository.updateRating(professorId);
        courseRepository.updateRating(courseId);
    }
    @Override
    public List<Comment> getCommentsByProfessorId(Integer professorId) {
        return commentRepository.getCommentsByProfessorId(professorId);
    }

    @Override
    public List<Comment> getCommentsByCourseId(Integer courseId) {
        return commentRepository.getCommentsByCourseId(courseId);
    }

    @Override
    public void deleteComment(Integer commentId) {
        commentRepository.deleteComment(commentId);
    }
}
