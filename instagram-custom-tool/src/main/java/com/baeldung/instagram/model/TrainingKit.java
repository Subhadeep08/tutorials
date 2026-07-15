package com.baeldung.instagram.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "training_kit")
public class TrainingKit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String instagramHandle;

    @Column(length = 2000)
    private String creatorSnapshot;

    @Column(length = 5000)
    private String writingSamples;

    @Column(length = 2000)
    private String targetAudience;

    @Column(length = 2000)
    private String styleGuide;

    @Column(length = 2000)
    private String goalsAndBoundaries;

    @Column(length = 2000)
    private String personalContext;

    public TrainingKit() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstagramHandle() {
        return instagramHandle;
    }

    public void setInstagramHandle(String instagramHandle) {
        this.instagramHandle = instagramHandle;
    }

    public String getCreatorSnapshot() {
        return creatorSnapshot;
    }

    public void setCreatorSnapshot(String creatorSnapshot) {
        this.creatorSnapshot = creatorSnapshot;
    }

    public String getWritingSamples() {
        return writingSamples;
    }

    public void setWritingSamples(String writingSamples) {
        this.writingSamples = writingSamples;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public String getStyleGuide() {
        return styleGuide;
    }

    public void setStyleGuide(String styleGuide) {
        this.styleGuide = styleGuide;
    }

    public String getGoalsAndBoundaries() {
        return goalsAndBoundaries;
    }

    public void setGoalsAndBoundaries(String goalsAndBoundaries) {
        this.goalsAndBoundaries = goalsAndBoundaries;
    }

    public String getPersonalContext() {
        return personalContext;
    }

    public void setPersonalContext(String personalContext) {
        this.personalContext = personalContext;
    }

}
