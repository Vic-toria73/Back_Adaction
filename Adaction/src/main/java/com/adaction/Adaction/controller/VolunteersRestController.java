package com.adaction.Adaction.controller;

import com.adaction.Adaction.model.Volunteers;
import com.adaction.Adaction.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/volunteers")
public class VolunteersRestController {

    @Autowired
    private VolunteerRepository volunteerRepository;

    @GetMapping
    public List<Volunteers> getVolunteers() {
        // Récupère tous les volontaires depuis la DB via le repository
        return volunteerRepository.findAll();
    }
}
