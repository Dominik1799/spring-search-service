package com.adversea.searchservice.controller;

import com.adversea.searchservice.service.SourcesService;
import jakarta.validation.constraints.NotNull;
import org.SwaggerCodeGenAdversea.api.SourcesApi;
import org.SwaggerCodeGenAdversea.model.SourcesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SourcesController implements SourcesApi {
    @Autowired
    SourcesService service;


    @Override
    public ResponseEntity<SourcesResponse> getSources(@NotNull String entityId) {
        SourcesResponse response = service.getSourcesForEntity(entityId);
        return ResponseEntity.ok(response);
    }
}
