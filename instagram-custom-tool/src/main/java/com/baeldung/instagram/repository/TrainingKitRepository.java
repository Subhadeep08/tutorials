package com.baeldung.instagram.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baeldung.instagram.model.TrainingKit;

public interface TrainingKitRepository extends JpaRepository<TrainingKit, Long> {

    Optional<TrainingKit> findByInstagramHandle(String instagramHandle);

}
