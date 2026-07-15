package com.baeldung.instagram.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baeldung.instagram.model.TrainingKit;
import com.baeldung.instagram.repository.TrainingKitRepository;

@Service
public class TrainingKitService {

    private final TrainingKitRepository repository;

    public TrainingKitService(TrainingKitRepository repository) {
        this.repository = repository;
    }

    public TrainingKit save(TrainingKit trainingKit) {
        return repository.save(trainingKit);
    }

    public TrainingKit findById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Training kit not found with id: " + id));
    }

    public TrainingKit findByHandle(String handle) {
        return repository.findByInstagramHandle(handle)
            .orElseThrow(() -> new IllegalArgumentException("Training kit not found for handle: " + handle));
    }

    public List<TrainingKit> findAll() {
        return repository.findAll();
    }

    public TrainingKit update(Long id, TrainingKit updated) {
        TrainingKit existing = findById(id);
        if (updated.getCreatorSnapshot() != null) {
            existing.setCreatorSnapshot(updated.getCreatorSnapshot());
        }
        if (updated.getWritingSamples() != null) {
            existing.setWritingSamples(updated.getWritingSamples());
        }
        if (updated.getTargetAudience() != null) {
            existing.setTargetAudience(updated.getTargetAudience());
        }
        if (updated.getStyleGuide() != null) {
            existing.setStyleGuide(updated.getStyleGuide());
        }
        if (updated.getGoalsAndBoundaries() != null) {
            existing.setGoalsAndBoundaries(updated.getGoalsAndBoundaries());
        }
        if (updated.getPersonalContext() != null) {
            existing.setPersonalContext(updated.getPersonalContext());
        }
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
