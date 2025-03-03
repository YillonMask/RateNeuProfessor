package com.rateneuprofessor.demo.serviceTest;

import com.rateneuprofessor.demo.entity.Professor;
import com.rateneuprofessor.demo.service.ProfessorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProfessorServiceImplTest {
    @Autowired
    ProfessorService professorService;

    @Test
    public void testAddProfessor() {
        professorService.addProfessor("Jane Doe", 3);
        professorService.addProfessor("John Doe", 2);
        List<Professor> profTestOne = professorService.searchProfessorByName("Jane Doe");
        assertEquals(1, profTestOne.size());
        List<Professor> profTestTwo = professorService.searchProfessorByName("John Doe");
        assertEquals(1, profTestTwo.size());
        professorService.deleteProfessor(profTestOne.get(0).getProfessorId());
        professorService.deleteProfessor(profTestTwo.get(0).getProfessorId());
    }

    @Test
    public void testSearchProfessorByName() {
        List<Professor> profs = professorService.searchProfessorByName("Mark Miller");
        assertEquals(1, profs.size());
        List<Professor> profs2 = professorService.searchProfessorByName("Prof Not Found");
        assertEquals(0, profs2.size());
    }

    @Test
    public void testDeleteProfessor() {
        professorService.addProfessor("Test Delete Prof", 1);
        List<Professor> profs = professorService.searchProfessorByName("Test Delete Prof");
        assertEquals(1, profs.size());
        professorService.deleteProfessor(profs.get(0).getProfessorId());
        List<Professor> profsAfterDelete = professorService.searchProfessorByName("Test Delete Prof");
        assertEquals(0, profsAfterDelete.size());
    }
}
