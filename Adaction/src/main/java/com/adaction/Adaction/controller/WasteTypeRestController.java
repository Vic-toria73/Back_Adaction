package com.adaction.Adaction.controller;

import com.adaction.Adaction.model.Waste_Type;
import com.adaction.Adaction.repository.WasteTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/waste-type")
@CrossOrigin(origins = "http://localhost:5173")
public class WasteTypeRestController {

    @Autowired
    private WasteTypeRepository wasteTypeRepository;

    @GetMapping
    public List<Waste_Type> getAllWasteTypes() {
        return wasteTypeRepository.findAll();
    }
}