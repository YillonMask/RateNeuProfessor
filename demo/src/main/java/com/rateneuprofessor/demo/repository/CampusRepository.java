package com.rateneuprofessor.demo.repository;

import java.util.List;

import com.rateneuprofessor.demo.entity.Campus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Repository interface for Campus repository.
 */
@Mapper
public interface CampusRepository {
    /**
     * Get all campuses.
     *
     * @return List of all campuses.
     */
    @Select("SELECT * FROM campus")
    public List<Campus> getAllCampus();
}
