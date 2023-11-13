package com.adversea.searchservice.service;

import com.adversea.searchservice.repository.SearchRepositoryElastic;
import com.adversea.searchservice.repository.entity.SearchEntityModel;
import com.adversea.searchservice.utility.Mapper;
import org.SwaggerCodeGenAdversea.model.SearchEntityResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
    @Autowired
    SearchRepositoryElastic repository;

    @Autowired
    Mapper mapper;

    public List<SearchEntityResult> search(String name) {
        List<SearchEntityResult> convertedResult = new ArrayList<>();
        List<SearchEntityModel> result;
        result = repository.searchDatabaseByName(name, PageRequest.of(0,3));
        for (SearchEntityModel model : result) {
            convertedResult.add(mapper.modelToSearchResponse(model));
        }
        return convertedResult;
    }
}
