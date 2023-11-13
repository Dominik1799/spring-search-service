package com.adversea.searchservice.controller;

import com.adversea.searchservice.service.DetailService;
import org.SwaggerCodeGenAdversea.api.DetailApi;
import org.SwaggerCodeGenAdversea.model.DetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DetailController implements DetailApi {

    @Autowired
    DetailService service;

    @Override
    public ResponseEntity<DetailResponse> getDetail(String entityId) {
        DetailResponse response = service.getDetailForEntity(entityId);
        return ResponseEntity.ok(response);
    }
}
