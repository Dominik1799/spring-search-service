package com.adversea.searchservice.service;

import com.adversea.searchservice.repository.SearchRepositoryElastic;
import com.adversea.searchservice.repository.entity.SearchEntityModel;
import com.adversea.searchservice.utility.Mapper;
import org.SwaggerCodeGenAdversea.model.SearchEntityResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;

class SearchServiceTest {

    @Mock
    private SearchRepositoryElastic repository;

    @Mock
    private Mapper mapper;

    @InjectMocks
    private SearchService searchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void searchShouldReturnResults() {
        // given
        String name = "testName";
        List<SearchEntityModel> mockResult = new ArrayList<>();
        // When
        Mockito.when(repository.searchDatabaseByName(eq(name), any(PageRequest.class))).thenReturn(mockResult);
        Mockito.when(mapper.modelToSearchResponse(any(SearchEntityModel.class))).thenReturn(new SearchEntityResult());
        List<SearchEntityResult> result = searchService.search(name);
        // Then
        Mockito.verify(repository, times(1)).searchDatabaseByName(eq(name), any(PageRequest.class));
        Mockito.verify(mapper, times(mockResult.size())).modelToSearchResponse(any(SearchEntityModel.class));
        assertEquals(mockResult.size(), result.size());
    }
}
