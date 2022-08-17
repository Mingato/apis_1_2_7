package com.netagentciadigital.api.controller;

import com.netagentciadigital.api.model.Attribute;
import com.netagentciadigital.api.model.response.ApiResponseBody;
import com.netagentciadigital.api.service.AttributeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@Slf4j
@RestController
@RequestMapping("/v1/attributes")
public class AttributeController {


    private final AttributeService attributeService;

    @Autowired
    public AttributeController(AttributeService attributeService) {
        this.attributeService = attributeService;
    }

    @GetMapping
    public ResponseEntity<ApiResponseBody> findAll(){
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(attributeService.findAll())
            .build();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseBody> findById(@PathVariable("id") String id){
        log.info(String.valueOf(id));
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(attributeService.findById(id))
            .build();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponseBody> create(@RequestBody @Valid Attribute attribute){
        attribute = attributeService.insert(attribute);
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(attribute)
            .build();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseBody> update(@PathVariable("id") String id, @RequestBody @Valid Attribute attribute){
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(attributeService.update(id, attribute))
            .build();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
