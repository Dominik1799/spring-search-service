package com.adversea.searchservice.utility;


import com.adversea.searchservice.repository.entity.SearchEntityModel;
import org.SwaggerCodeGenAdversea.model.BasicEntityInfo;
import org.SwaggerCodeGenAdversea.model.DetailResponse;
import org.SwaggerCodeGenAdversea.model.SearchEntityResult;
import org.SwaggerCodeGenAdversea.model.SourcesResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class Mapper {

    private static final int TOP_NUMBER_OF_LOCATIONS = 3;
    private static final int TOP_NUMBER_OF_ALIASES = 3;


    public SourcesResponse modelToSourcesResult(SearchEntityModel model) {
        SourcesResponse result = new SourcesResponse();
        BasicEntityInfo basicEntityInfo = new BasicEntityInfo();
        basicEntityInfo.setAliases(model.getAliases());
        basicEntityInfo.setName(model.getName());
        basicEntityInfo.setType(model.getType());
        result.setBasicInfo(basicEntityInfo);
        result.setAms(model.getAmsArticles());
        result.setPep(model.getPepRecord());
        result.setAllArticles(model.getAllArticles());
        result.setSl(model.getSlRecord());
        return result;
    }

    public SearchEntityResult modelToSearchResponse(SearchEntityModel model) {
        SearchEntityResult response = new SearchEntityResult();
        response.setId(model.getId());
        response.setName(model.getName());
        response.setNameAscii(model.getNameAscii());
        response.setType(SearchEntityResult.TypeEnum.fromValue(model.getType()));
        response.setSource(model.getInformationSource());
        response.setLocations(sortHistogram(model.getLocations(), TOP_NUMBER_OF_LOCATIONS));
        response.setAliases(sortHistogram(model.getAliasesCount(), TOP_NUMBER_OF_ALIASES));
        response.setAmsRecordsCount(model.getAmsArticles().size());
        response.setPepRecordsCount(model.getPepRecord().size());
        response.setSlRecordsCount(model.getSlRecord().size());
        response.setAllArticlesRecordsCount(model.getAllArticles().size());
        return response;

    }

    private List<String> sortHistogram(Map<String, Integer> locations, int numberOfLocations) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(locations.entrySet());
        list.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));

        List<String> sortedLocations = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedLocations.add(entry.getKey());
        }
        return sortedLocations.stream().limit(numberOfLocations).collect(Collectors.toList());
    }


    public DetailResponse modelToDetailResult(SearchEntityModel searchEntityModel) {
        DetailResponse result = new DetailResponse();
        result.setId(searchEntityModel.getId());
        result.setName(searchEntityModel.getName());
        result.setNameAscii(searchEntityModel.getNameAscii());
        result.setType(DetailResponse.TypeEnum.fromValue(searchEntityModel.getType()));
        result.setLocationsHistogram(searchEntityModel.getLocations());
        result.setSource(searchEntityModel.getInformationSource());
        result.setAmsRecordsCount(searchEntityModel.getAmsArticles().size());
        result.setPepRecordsCount(searchEntityModel.getPepRecord().size());
        result.setSlRecordsCount(searchEntityModel.getSlRecord().size());
        result.setAllArticlesRecordsCount(searchEntityModel.getAllArticles().size());
        result.setAliasesHistogram(searchEntityModel.getAliasesCount());
        return result;
    }
}
