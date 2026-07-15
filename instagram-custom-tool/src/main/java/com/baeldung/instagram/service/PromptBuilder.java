package com.baeldung.instagram.service;

import org.springframework.stereotype.Component;

import com.baeldung.instagram.model.ContentType;
import com.baeldung.instagram.model.TrainingKit;

@Component
public class PromptBuilder {

    public String buildSystemPrompt(TrainingKit kit) {
        StringBuilder sb = new StringBuilder();
        sb.append("You are an expert Instagram content creator and strategist. ");
        sb.append("You create content that matches the creator's unique voice and style.\n\n");

        sb.append("## Creator Profile\n");
        sb.append("Instagram Handle: @").append(kit.getInstagramHandle()).append("\n\n");

        if (kit.getCreatorSnapshot() != null) {
            sb.append("## Creator Snapshot\n");
            sb.append(kit.getCreatorSnapshot()).append("\n\n");
        }

        if (kit.getTargetAudience() != null) {
            sb.append("## Target Audience\n");
            sb.append(kit.getTargetAudience()).append("\n\n");
        }

        if (kit.getStyleGuide() != null) {
            sb.append("## Style Guide\n");
            sb.append(kit.getStyleGuide()).append("\n\n");
        }

        if (kit.getGoalsAndBoundaries() != null) {
            sb.append("## Goals & Boundaries\n");
            sb.append(kit.getGoalsAndBoundaries()).append("\n\n");
        }

        if (kit.getPersonalContext() != null) {
            sb.append("## Personal Context\n");
            sb.append(kit.getPersonalContext()).append("\n\n");
        }

        if (kit.getWritingSamples() != null) {
            sb.append("## Writing Samples (match this voice and tone)\n");
            sb.append(kit.getWritingSamples()).append("\n\n");
        }

        sb.append("Always write in the creator's authentic voice based on the profile above. ");
        sb.append("Never use generic or corporate language unless the creator's style calls for it.");

        return sb.toString();
    }

    public String buildUserPrompt(ContentType contentType, String topic, String additionalInstructions) {
        StringBuilder sb = new StringBuilder();

        switch (contentType) {
            case CAPTION -> {
                sb.append("Write an Instagram caption about: ").append(topic).append("\n");
                sb.append("Include a hook in the first line, a body with value, and a call-to-action. ");
                sb.append("Suggest relevant hashtags at the end.");
            }
            case REEL_SCRIPT -> {
                sb.append("Write a Reel script about: ").append(topic).append("\n");
                sb.append("Structure it with: Hook (first 3 seconds), Body (main content with timestamps), ");
                sb.append("CTA (closing call-to-action). Include on-screen text suggestions and audio/music notes.");
            }
            case STORY_SEQUENCE -> {
                sb.append("Create a Story sequence (5-7 slides) about: ").append(topic).append("\n");
                sb.append("For each slide, provide: visual description, text overlay, and any interactive element ");
                sb.append("(poll, question box, quiz, slider). Build engagement through the sequence.");
            }
            case CAROUSEL_OUTLINE -> {
                sb.append("Create a carousel post outline (8-10 slides) about: ").append(topic).append("\n");
                sb.append("Slide 1 should be a scroll-stopping hook. Each subsequent slide should deliver one key point. ");
                sb.append("The final slide should have a strong CTA. Include text for each slide and design notes.");
            }
            case CONTENT_CALENDAR -> {
                sb.append("Create a 7-day Instagram content calendar about: ").append(topic).append("\n");
                sb.append("For each day, include: content type (Reel/Carousel/Static/Story), topic, ");
                sb.append("caption hook, best posting time suggestion, and hashtag group.");
            }
            case HASHTAG_STRATEGY -> {
                sb.append("Create a hashtag strategy for the niche/topic: ").append(topic).append("\n");
                sb.append("Provide 3 groups: high-volume (500K+ posts), medium (50K-500K), and niche (<50K). ");
                sb.append("Include 10 hashtags per group. Explain the mix strategy.");
            }
            case BIO_OPTIMIZATION -> {
                sb.append("Optimize an Instagram bio for: ").append(topic).append("\n");
                sb.append("Provide 3 bio variations. Each should include: name field optimization, ");
                sb.append("a clear value proposition, social proof element, and CTA with emoji usage.");
            }
            case DM_REPLY_TEMPLATE -> {
                sb.append("Create DM reply templates for the scenario: ").append(topic).append("\n");
                sb.append("Provide templates for: initial welcome, FAQ response, product/service inquiry, ");
                sb.append("collaboration request, and a follow-up sequence (3 messages).");
            }
        }

        if (additionalInstructions != null && !additionalInstructions.isBlank()) {
            sb.append("\n\nAdditional instructions: ").append(additionalInstructions);
        }

        return sb.toString();
    }

}
