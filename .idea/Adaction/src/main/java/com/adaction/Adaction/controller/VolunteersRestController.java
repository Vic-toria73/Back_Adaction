package com.adaction.Adaction.controller;

import com.adaction.Adaction.model.Volunteers;
import com.adaction.Adaction.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    //  GET all volunteers
    @GetMapping
    public List<Volunteers> getVolunteers() {
        return volunteerRepository.findAll();
    }

    //  POST new volunteer
    @PostMapping
    public Volunteers addVolunteer(@RequestBody Volunteers volunteer) {
        volunteerRepository.save(volunteer);
        return volunteer;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVolunteer(@PathVariable int id) {
        try {
            volunteerRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


}
