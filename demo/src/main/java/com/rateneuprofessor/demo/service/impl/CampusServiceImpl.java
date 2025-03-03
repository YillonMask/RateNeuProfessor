package com.rateneuprofessor.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rateneuprofessor.demo.entity.Campus;
import com.rateneuprofessor.demo.repository.CampusRepository;

import com.rateneuprofessor.demo.service.CampusService;

import java.util.List;

@Service
public class CampusServiceImpl implements CampusService {
    @Autowired
    private CampusRepository campusRepository;

    @Override
    public List<Campus> getAllCampus() {
        return campusRepository.getAllCampus();
    }
}
