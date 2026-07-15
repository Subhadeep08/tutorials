package com.baeldung.instagram.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ContentRequest {

    @NotNull
    private Long trainingKitId;

    @NotBlank
    private String topic;

    @NotNull
    private ContentType contentType;

    private String additionalInstructions;

    public Long getTrainingKitId() {
        return trainingKitId;
    }

    public void setTrainingKitId(Long trainingKitId) {
        this.trainingKitId = trainingKitId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public String getAdditionalInstructions() {
        return additionalInstructions;
    }

    public void setAdditionalInstructions(String additionalInstructions) {
        this.additionalInstructions = additionalInstructions;
    }

}
