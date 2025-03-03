package com.rateneuprofessor.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rateneuprofessor.demo.entity.Campus;
import com.rateneuprofessor.demo.service.CampusService;
import com.rateneuprofessor.demo.service.impl.CampusServiceImpl;
import com.rateneuprofessor.demo.utils.GsonTools;

import java.util.List;

@RestController
public class CampusController {
    @Autowired
    private CampusService campusService;

    @GetMapping("/campus")
    public String getAllCampus(){
        List<Campus> campus = campusService.getAllCampus();
        if (campus != null){
            return GsonTools.success("Get all campus successfully", campus);
        }else{
            return GsonTools.error("Cannot get campus");
        }
    }
}
