package com.baeldung.instagram.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.baeldung.instagram.model.ContentRequest;
import com.baeldung.instagram.model.ContentType;
import com.baeldung.instagram.model.GeneratedContent;
import com.baeldung.instagram.model.TrainingKit;

@ExtendWith(MockitoExtension.class)
class ContentGenerationServiceUnitTest {

    @Mock
    private TrainingKitService trainingKitService;

    @Spy
    private PromptBuilder promptBuilder;

    @InjectMocks
    private ContentGenerationService contentGenerationService;

    @Test
    void whenGenerateContent_thenReturnsGeneratedContent() {
        TrainingKit kit = new TrainingKit();
        kit.setId(1L);
        kit.setInstagramHandle("test_creator");
        kit.setCreatorSnapshot("I teach fitness tips");

        when(trainingKitService.findById(1L)).thenReturn(kit);

        ContentGenerationService spyService = org.mockito.Mockito.spy(contentGenerationService);
        org.mockito.Mockito.doReturn("Generated caption content here")
            .when(spyService).callClaudeApi(anyString(), anyString());

        ContentRequest request = new ContentRequest();
        request.setTrainingKitId(1L);
        request.setTopic("morning workout");
        request.setContentType(ContentType.CAPTION);

        GeneratedContent result = spyService.generate(request);

        assertNotNull(result);
        assertEquals(ContentType.CAPTION, result.getContentType());
        assertEquals("morning workout", result.getTopic());
        assertEquals("Generated caption content here", result.getContent());
        assertEquals("test_creator", result.getInstagramHandle());
    }

}
