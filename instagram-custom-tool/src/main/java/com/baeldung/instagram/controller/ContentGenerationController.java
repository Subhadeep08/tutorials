package com.baeldung.instagram.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baeldung.instagram.model.ContentRequest;
import com.baeldung.instagram.model.GeneratedContent;
import com.baeldung.instagram.service.ContentGenerationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/content")
public class ContentGenerationController {

    private final ContentGenerationService contentGenerationService;

    public ContentGenerationController(ContentGenerationService contentGenerationService) {
        this.contentGenerationService = contentGenerationService;
    }

    @PostMapping("/generate")
    public GeneratedContent generate(@Valid @RequestBody ContentRequest request) {
        return contentGenerationService.generate(request);
    }

}
