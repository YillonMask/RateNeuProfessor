package com.rateneuprofessor.demo.repositoryTest;

import com.rateneuprofessor.demo.entity.Campus;
import com.rateneuprofessor.demo.repository.CampusRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CampusRepositoryTest {
    @Autowired
    CampusRepository campusRepository;
    @Test
    public void testGetAllCampus() {

        List<Campus> campus = campusRepository.getAllCampus();
        assertEquals("Online", campus.get(0).getCampusName());
        assertEquals("Boston", campus.get(1).getCampusName());
        assertEquals("Silicon Valley", campus.get(2).getCampusName());
        assertEquals("Seattle", campus.get(3).getCampusName());
        assertEquals("Oakland", campus.get(4).getCampusName());
    }
}
