package com.baeldung.instagram.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.baeldung.instagram.model.ContentRequest;
import com.baeldung.instagram.model.GeneratedContent;
import com.baeldung.instagram.model.TrainingKit;

@Service
public class ContentGenerationService {

    private static final Logger log = LoggerFactory.getLogger(ContentGenerationService.class);

    private final WebClient claudeWebClient;
    private final TrainingKitService trainingKitService;
    private final PromptBuilder promptBuilder;

    @Value("${claude.model}")
    private String model;

    @Value("${claude.max-tokens}")
    private int maxTokens;

    public ContentGenerationService(WebClient claudeWebClient, TrainingKitService trainingKitService,
        PromptBuilder promptBuilder) {
        this.claudeWebClient = claudeWebClient;
        this.trainingKitService = trainingKitService;
        this.promptBuilder = promptBuilder;
    }

    public GeneratedContent generate(ContentRequest request) {
        TrainingKit kit = trainingKitService.findById(request.getTrainingKitId());

        String systemPrompt = promptBuilder.buildSystemPrompt(kit);
        String userPrompt = promptBuilder.buildUserPrompt(
            request.getContentType(), request.getTopic(), request.getAdditionalInstructions());

        String content = callClaudeApi(systemPrompt, userPrompt);

        return new GeneratedContent(request.getContentType(), request.getTopic(), content, kit.getInstagramHandle());
    }

    String callClaudeApi(String systemPrompt, String userPrompt) {
        Map<String, Object> requestBody = Map.of(
            "model", model,
            "max_tokens", maxTokens,
            "system", systemPrompt,
            "messages", List.of(
                Map.of("role", "user", "content", userPrompt)
            )
        );

        log.info("Sending content generation request to Claude API");

        Map<String, Object> response = claudeWebClient.post()
            .uri("/v1/messages")
            .bodyValue(requestBody)
            .retrieve()
            .bodyToMono(Map.class)
            .block();

        return extractContent(response);
    }

    @SuppressWarnings("unchecked")
    private String extractContent(Map<String, Object> response) {
        if (response == null || !response.containsKey("content")) {
            throw new RuntimeException("Empty response from Claude API");
        }
        List<Map<String, Object>> contentBlocks = (List<Map<String, Object>>) response.get("content");
        if (contentBlocks.isEmpty()) {
            throw new RuntimeException("No content blocks in Claude API response");
        }
        return (String) contentBlocks.get(0).get("text");
    }

}
