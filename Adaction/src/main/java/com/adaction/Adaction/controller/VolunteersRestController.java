package com.adaction.Adaction.controller;

import com.adaction.Adaction.model.Volunteers;
import com.adaction.Adaction.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/volunteers")
@CrossOrigin(origins = "http://localhost:5173")

public class VolunteersRestController {
    private final VolunteerRepository volunteerRepository;
    @Autowired
    public VolunteersRestController(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;


    }
    @GetMapping
    public List<Volunteers> getVolunteers() {
        return volunteerRepository.findAll();
    }
}
