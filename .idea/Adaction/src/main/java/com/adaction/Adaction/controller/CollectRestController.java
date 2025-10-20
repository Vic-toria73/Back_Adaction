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
    public List<Collect> getAllCollects() {
        return collectRepository.findAll();
    }

    @PostMapping
    public Collect saveCollect(@RequestBody Collect collect) {
        return collectRepository.save(collect);
    }

}