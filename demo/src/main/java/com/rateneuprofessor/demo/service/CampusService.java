package com.rateneuprofessor.demo.service;

import java.util.List;

import com.rateneuprofessor.demo.entity.Campus;
/**
 * Service interface for Campus service.
 */
public interface CampusService {
    /**
     * Get all campuses.
     *
     * @return List of all campuses.
     */
    List<Campus> getAllCampus();
}
