package com.adversea.searchservice.service;

import com.adversea.searchservice.repository.SearchRepositoryElastic;
import com.adversea.searchservice.repository.entity.SearchEntityModel;
import com.adversea.searchservice.utility.Mapper;
import org.SwaggerCodeGenAdversea.model.SourcesResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

class SourcesServiceTest {

    @Mock
    private SearchRepositoryElastic repository;

    @Mock
    private Mapper mapper;

    @InjectMocks
    private SourcesService sourcesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getSourcesForEntityWhenEntityExists() {
        // Given
        String entityId = "123";
        SearchEntityModel searchEntityModel = new SearchEntityModel();
        SourcesResponse expectedResponse = new SourcesResponse();
        // When
        Mockito.when(repository.findById(entityId)).thenReturn(Optional.of(searchEntityModel));
        Mockito.when(mapper.modelToSourcesResult(searchEntityModel)).thenReturn(expectedResponse);

        SourcesResponse actualResponse = sourcesService.getSourcesForEntity(entityId);

        // Then
        assertEquals(expectedResponse, actualResponse);
        Mockito.verify(repository, times(1)).findById(entityId);
        Mockito.verify(mapper, times(1)).modelToSourcesResult(searchEntityModel);
    }

    @Test
    void getSourcesForEntityShouldThrowExceptionForNonExistingEntity() {
        // Given
        String entityId = "456";
        // When
        Mockito.when(repository.findById(entityId)).thenReturn(Optional.empty());

        // Then
        assertThrows(ResponseStatusException.class, () -> sourcesService.getSourcesForEntity(entityId));
        Mockito.verify(repository, times(1)).findById(entityId);
        Mockito.verify(mapper, never()).modelToSourcesResult(any());
    }
}
