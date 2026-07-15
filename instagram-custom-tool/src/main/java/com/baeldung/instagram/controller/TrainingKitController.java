package com.baeldung.instagram.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.baeldung.instagram.model.TrainingKit;
import com.baeldung.instagram.service.TrainingKitService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/training-kit")
public class TrainingKitController {

    private final TrainingKitService trainingKitService;

    public TrainingKitController(TrainingKitService trainingKitService) {
        this.trainingKitService = trainingKitService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingKit create(@Valid @RequestBody TrainingKit trainingKit) {
        return trainingKitService.save(trainingKit);
    }

    @GetMapping("/{id}")
    public TrainingKit getById(@PathVariable Long id) {
        return trainingKitService.findById(id);
    }

    @GetMapping("/handle/{handle}")
    public TrainingKit getByHandle(@PathVariable String handle) {
        return trainingKitService.findByHandle(handle);
    }

    @GetMapping
    public List<TrainingKit> getAll() {
        return trainingKitService.findAll();
    }

    @PutMapping("/{id}")
    public TrainingKit update(@PathVariable Long id, @RequestBody TrainingKit trainingKit) {
        return trainingKitService.update(id, trainingKit);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        trainingKitService.delete(id);
    }

}
