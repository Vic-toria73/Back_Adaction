package com.adaction.Adaction.controller;

import com.adaction.Adaction.model.Volunteers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VolunteersRestController {
    @GetMapping("/api/volunteers")
    public List<String> getVolunteers(){
        return List.of("Bob");
    }

}
