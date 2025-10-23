package com.adaction.Adaction.controller;

import com.adaction.Adaction.model.Volunteers;
import com.adaction.Adaction.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;  // <-- IMPORT IMPORTANT
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

    // GET all volunteers
    @GetMapping
    public List<Volunteers> getVolunteers() {
        return volunteerRepository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Volunteers> getVolunteerById(@PathVariable int id) {
        return volunteerRepository.findById(id)
                .map(volunteer -> ResponseEntity.ok(volunteer))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST new volunteer
    @PostMapping
    public Volunteers addVolunteer(@RequestBody Volunteers volunteer) {
        return volunteerRepository.save(volunteer);
    }

    // DELETE volunteer by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVolunteer(@PathVariable int id) {
        boolean deleted = volunteerRepository.deleteById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    //  mettre à jour un bénévole
    @PutMapping("/{id}")
    public ResponseEntity<Volunteers> updateVolunteer(
            @PathVariable int id,
            @RequestBody Volunteers updatedVolunteer) {

        return volunteerRepository.findById(id)
                .map(existingVolunteer -> {

                    existingVolunteer.setFirstname(updatedVolunteer.getFirstname());
                    existingVolunteer.setLastname(updatedVolunteer.getLastname());
                    existingVolunteer.setMail(updatedVolunteer.getMail());
                    existingVolunteer.setPassword(updatedVolunteer.getPassword());
                    existingVolunteer.setCity(updatedVolunteer.getCity());

                    // Sauvegarder l’objet existant
                    volunteerRepository.save(existingVolunteer);
                    return ResponseEntity.ok(existingVolunteer);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}