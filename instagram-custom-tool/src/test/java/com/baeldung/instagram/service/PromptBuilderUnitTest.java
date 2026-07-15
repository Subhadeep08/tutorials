package com.baeldung.instagram.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.baeldung.instagram.model.ContentType;
import com.baeldung.instagram.model.TrainingKit;

class PromptBuilderUnitTest {

    private PromptBuilder promptBuilder;

    @BeforeEach
    void setUp() {
        promptBuilder = new PromptBuilder();
    }

    @Test
    void whenBuildSystemPrompt_thenContainsAllKitSections() {
        TrainingKit kit = createFullTrainingKit();

        String prompt = promptBuilder.buildSystemPrompt(kit);

        assertTrue(prompt.contains("@test_creator"));
        assertTrue(prompt.contains("Creator Snapshot"));
        assertTrue(prompt.contains("I help people learn to code"));
        assertTrue(prompt.contains("Target Audience"));
        assertTrue(prompt.contains("Aspiring developers"));
        assertTrue(prompt.contains("Style Guide"));
        assertTrue(prompt.contains("Conversational and encouraging"));
        assertTrue(prompt.contains("Goals & Boundaries"));
        assertTrue(prompt.contains("Grow to 50K followers"));
        assertTrue(prompt.contains("Personal Context"));
        assertTrue(prompt.contains("Self-taught developer"));
        assertTrue(prompt.contains("Writing Samples"));
        assertTrue(prompt.contains("Here's what nobody tells you"));
    }

    @Test
    void whenBuildSystemPromptWithPartialKit_thenSkipsMissingSections() {
        TrainingKit kit = new TrainingKit();
        kit.setInstagramHandle("minimal_creator");
        kit.setCreatorSnapshot("Just getting started");

        String prompt = promptBuilder.buildSystemPrompt(kit);

        assertTrue(prompt.contains("@minimal_creator"));
        assertTrue(prompt.contains("Creator Snapshot"));
        assertFalse(prompt.contains("Writing Samples"));
        assertFalse(prompt.contains("Target Audience"));
    }

    @Test
    void whenBuildCaptionPrompt_thenContainsHookAndCta() {
        String prompt = promptBuilder.buildUserPrompt(ContentType.CAPTION, "morning routines", null);

        assertTrue(prompt.contains("morning routines"));
        assertTrue(prompt.contains("hook"));
        assertTrue(prompt.contains("call-to-action"));
        assertTrue(prompt.contains("hashtags"));
    }

    @Test
    void whenBuildReelScriptPrompt_thenContainsStructure() {
        String prompt = promptBuilder.buildUserPrompt(ContentType.REEL_SCRIPT, "coding tips", null);

        assertTrue(prompt.contains("coding tips"));
        assertTrue(prompt.contains("Hook"));
        assertTrue(prompt.contains("CTA"));
    }

    @Test
    void whenBuildContentCalendarPrompt_thenContains7Days() {
        String prompt = promptBuilder.buildUserPrompt(ContentType.CONTENT_CALENDAR, "fitness", null);

        assertTrue(prompt.contains("7-day"));
        assertTrue(prompt.contains("fitness"));
    }

    @Test
    void whenAdditionalInstructionsProvided_thenIncludedInPrompt() {
        String prompt = promptBuilder.buildUserPrompt(ContentType.CAPTION, "travel",
            "Keep it under 100 words");

        assertTrue(prompt.contains("travel"));
        assertTrue(prompt.contains("Keep it under 100 words"));
    }

    @Test
    void whenBuildHashtagStrategyPrompt_thenContainsVolumeGroups() {
        String prompt = promptBuilder.buildUserPrompt(ContentType.HASHTAG_STRATEGY, "photography", null);

        assertTrue(prompt.contains("high-volume"));
        assertTrue(prompt.contains("medium"));
        assertTrue(prompt.contains("niche"));
    }

    @Test
    void whenBuildCarouselPrompt_thenContainsSlideStructure() {
        String prompt = promptBuilder.buildUserPrompt(ContentType.CAROUSEL_OUTLINE, "productivity", null);

        assertTrue(prompt.contains("carousel"));
        assertTrue(prompt.contains("Slide 1"));
        assertTrue(prompt.contains("CTA"));
    }

    @Test
    void whenBuildStorySequencePrompt_thenContainsInteractiveElements() {
        String prompt = promptBuilder.buildUserPrompt(ContentType.STORY_SEQUENCE, "Q&A session", null);

        assertTrue(prompt.contains("Story sequence"));
        assertTrue(prompt.contains("poll"));
        assertTrue(prompt.contains("question box"));
    }

    @Test
    void whenBuildDmReplyPrompt_thenContainsTemplateTypes() {
        String prompt = promptBuilder.buildUserPrompt(ContentType.DM_REPLY_TEMPLATE, "product launch", null);

        assertTrue(prompt.contains("welcome"));
        assertTrue(prompt.contains("FAQ"));
        assertTrue(prompt.contains("follow-up"));
    }

    @Test
    void whenBuildBioOptimizationPrompt_thenContainsBioElements() {
        String prompt = promptBuilder.buildUserPrompt(ContentType.BIO_OPTIMIZATION, "fitness coach", null);

        assertTrue(prompt.contains("bio"));
        assertTrue(prompt.contains("value proposition"));
        assertTrue(prompt.contains("CTA"));
    }

    private TrainingKit createFullTrainingKit() {
        TrainingKit kit = new TrainingKit();
        kit.setInstagramHandle("test_creator");
        kit.setCreatorSnapshot("I help people learn to code through bite-sized visual content.");
        kit.setWritingSamples("Here's what nobody tells you about learning to code...");
        kit.setTargetAudience("Aspiring developers aged 20-35 who are career-switching into tech.");
        kit.setStyleGuide("Conversational and encouraging. Use short sentences. Avoid jargon.");
        kit.setGoalsAndBoundaries("Grow to 50K followers. Never promote get-rich-quick schemes.");
        kit.setPersonalContext("Self-taught developer who switched from finance. Based in NYC.");
        return kit;
    }

}
