package com.rateneuprofessor.demo.controller;

import com.rateneuprofessor.demo.service.CommentService;
import com.rateneuprofessor.demo.service.impl.CommentServiceImpl;
import com.rateneuprofessor.demo.utils.GsonTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    public String addComment(
            @RequestParam("course_id") Integer courseId,
            @RequestParam("rating") Integer rating,
            @RequestParam("content") String content
    ) {
        commentService.addComment(courseId, rating, content);
        return GsonTools.success("Comment added successfully");
    }

    @GetMapping
    public String getComments(
            @RequestParam("action") String action,
            @RequestParam(value = "professor_id", required = false) Integer professorId,
            @RequestParam(value = "course_id", required = false) Integer courseId
    ) {
        switch(action) {
            case "getCommentsByProfessorId":
                if (professorId == null) {
                    return GsonTools.error("Professor ID is required");
                }
                return GsonTools.success(
                        "Get comments by professor id successfully",
                        commentService.getCommentsByProfessorId(professorId)
                );

            case "getCommentsByCourseId":
                if (courseId == null) {
                    return GsonTools.error("Course ID is required");
                }
                return GsonTools.success(
                        "Get comments by course id successfully",
                        commentService.getCommentsByCourseId(courseId)
                );

            default:
                return GsonTools.error("Invalid action");
        }
    }
}