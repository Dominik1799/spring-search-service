package com.adversea.searchservice.service;

import com.adversea.searchservice.repository.SearchRepositoryElastic;
import com.adversea.searchservice.repository.entity.SearchEntityModel;
import com.adversea.searchservice.utility.Mapper;
import org.SwaggerCodeGenAdversea.model.DetailResponse;
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
import static org.mockito.ArgumentMatchers.anyString;

class DetailServiceTest {

    @Mock
    private SearchRepositoryElastic repository;

    @Mock
    private Mapper mapper;

    @InjectMocks
    private DetailService detailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getDetailForEntityShouldReturnValidResponse() {
        // Given
        String entityId = "existingId";
        SearchEntityModel entityModel = new SearchEntityModel();

        // When
        Mockito.when(repository.findById(entityId)).thenReturn(Optional.of(entityModel));
        Mockito.when(mapper.modelToDetailResult(entityModel)).thenReturn(new DetailResponse());

        DetailResponse result = detailService.getDetailForEntity(entityId);

        // Then
        Mockito.verify(repository, Mockito.times(1)).findById(entityId);
        Mockito.verify(mapper, Mockito.times(1)).modelToDetailResult(entityModel);
        assertEquals(DetailResponse.class, result.getClass());
    }

    @Test
    void getDetailEntityShouldThrowExceptionForNonExistingEntity() {
        // Given
        String entityId = "nonExistingId";
        // When
        Mockito.when(repository.findById(anyString())).thenReturn(Optional.empty());

        // Then
        assertThrows(ResponseStatusException.class, () -> detailService.getDetailForEntity(entityId));
    }
}
