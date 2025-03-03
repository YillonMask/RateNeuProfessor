package com.rateneuprofessor.demo.service.impl;

import com.rateneuprofessor.demo.entity.Professor;
import com.rateneuprofessor.demo.repository.ProfessorRepository;
//import com.rateneuprofessor.demo.repository.impl.ProfessorRepositoryImpl;
import com.rateneuprofessor.demo.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorServiceImpl implements ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;
    @Override
    public void addProfessor(String name, Integer campusId) {
        // add professor to database
        professorRepository.addProfessor(name, campusId);

    }

    @Override
    public List<Professor> searchProfessorByName(String name) {
        // search for professor by name
        return professorRepository.searchProfessorByName(name);
    }

    @Override
    public void deleteProfessor(Integer professorId) {
        // delete professor from database
        professorRepository.deleteProfessor(professorId);
    }
}
