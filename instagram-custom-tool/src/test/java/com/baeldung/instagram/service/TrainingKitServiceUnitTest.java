package com.baeldung.instagram.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.baeldung.instagram.model.TrainingKit;
import com.baeldung.instagram.repository.TrainingKitRepository;

@ExtendWith(MockitoExtension.class)
class TrainingKitServiceUnitTest {

    @Mock
    private TrainingKitRepository repository;

    @InjectMocks
    private TrainingKitService service;

    @Test
    void whenSaveTrainingKit_thenReturnsSaved() {
        TrainingKit kit = new TrainingKit();
        kit.setInstagramHandle("my_handle");
        kit.setCreatorSnapshot("I teach yoga");

        when(repository.save(any(TrainingKit.class))).thenReturn(kit);

        TrainingKit result = service.save(kit);

        assertNotNull(result);
        assertEquals("my_handle", result.getInstagramHandle());
        verify(repository).save(kit);
    }

    @Test
    void whenFindByIdExists_thenReturnsKit() {
        TrainingKit kit = new TrainingKit();
        kit.setId(1L);
        kit.setInstagramHandle("test");

        when(repository.findById(1L)).thenReturn(Optional.of(kit));

        TrainingKit result = service.findById(1L);

        assertEquals("test", result.getInstagramHandle());
    }

    @Test
    void whenFindByIdNotExists_thenThrowsException() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> service.findById(99L));
    }

    @Test
    void whenUpdatePartialFields_thenOnlyUpdatesProvided() {
        TrainingKit existing = new TrainingKit();
        existing.setId(1L);
        existing.setInstagramHandle("original");
        existing.setCreatorSnapshot("Old snapshot");
        existing.setTargetAudience("Old audience");

        TrainingKit update = new TrainingKit();
        update.setCreatorSnapshot("New snapshot");

        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(any(TrainingKit.class))).thenAnswer(inv -> inv.getArgument(0));

        TrainingKit result = service.update(1L, update);

        assertEquals("New snapshot", result.getCreatorSnapshot());
        assertEquals("Old audience", result.getTargetAudience());
    }

    @Test
    void whenFindByHandle_thenReturnsKit() {
        TrainingKit kit = new TrainingKit();
        kit.setInstagramHandle("yoga_daily");

        when(repository.findByInstagramHandle("yoga_daily")).thenReturn(Optional.of(kit));

        TrainingKit result = service.findByHandle("yoga_daily");

        assertEquals("yoga_daily", result.getInstagramHandle());
    }

    @Test
    void whenFindByHandleNotExists_thenThrowsException() {
        when(repository.findByInstagramHandle("nonexistent")).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> service.findByHandle("nonexistent"));
    }

}
