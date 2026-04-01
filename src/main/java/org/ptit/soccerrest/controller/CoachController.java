package org.ptit.soccerrest.controller;

import java.util.List;
import org.ptit.soccerrest.domain.model.Coach;
import org.ptit.soccerrest.service.CoachService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coaches")
@CrossOrigin("*")
public class CoachController {

    private final CoachService coachService;

    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @GetMapping
    public List<Coach> all() {
        return coachService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coach> getById(@PathVariable Long id) {
        return coachService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Coach> create(@RequestBody Coach coach) {
        return ResponseEntity.ok(coachService.save(coach));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coach> update(@PathVariable Long id, @RequestBody Coach coach) {
        return coachService.update(id, coach)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (coachService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

