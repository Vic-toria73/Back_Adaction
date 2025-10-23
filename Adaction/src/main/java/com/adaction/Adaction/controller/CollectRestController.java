package com.adaction.Adaction.controller;

import com.adaction.Adaction.model.Collect;
import com.adaction.Adaction.repository.CollectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/collects")
public class CollectRestController {

    @Autowired
    private CollectRepository collectRepository;

    @GetMapping
    public List<Collect> getAllCollects(@RequestParam(required = false) Integer volunteerId) {
        if (volunteerId != null) {
            // Si un volunteerId est fourni → on filtre
            return collectRepository.findByVolunteerId(volunteerId);
        } else {
            // Sinon, on retourne tout
            return collectRepository.findAll();
        }
    }

    @PostMapping
    public Collect saveCollect(@RequestBody Collect collect) {
        return collectRepository.save(collect);
    }
}