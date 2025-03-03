package com.rateneuprofessor.demo.serviceTest;

import com.rateneuprofessor.demo.entity.Campus;
import com.rateneuprofessor.demo.service.impl.CampusServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CampusServiceImplTest {
    @Autowired
    CampusServiceImpl campusService;
    @Test
    public void testGetAllCampuses(){
        List<Campus> campus = campusService.getAllCampus();
        assertEquals(14, campus.size());
        assertEquals("Online", campus.get(0).getCampusName());
    }
}
