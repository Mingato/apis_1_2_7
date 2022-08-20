package com.netagentciadigital.api.controller;

import com.netagentciadigital.api.model.Manufacturer;
import com.netagentciadigital.api.model.response.ApiResponseBody;
import com.netagentciadigital.api.service.ManufacturerService;
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
@RequestMapping("/v1/manufacturer")
public class ManufacturerController {


    private final ManufacturerService manufacturerService;


    @Autowired
    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public ResponseEntity<ApiResponseBody> findAll(){
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(manufacturerService.findAll())
            .build();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseBody> findById(@PathVariable("id") Long id){
        log.info(String.valueOf(id));
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(manufacturerService.findById(id))
            .build();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponseBody> insert(@RequestBody @Valid Manufacturer manufacturer){
        manufacturer = manufacturerService.insert(manufacturer);
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(manufacturer)
            .build();
        result.put("manufacturerId",manufacturer.getId());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseBody> update(@PathVariable("id") Long id, @RequestBody @Valid Manufacturer manufacturer){
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(manufacturerService.update(id, manufacturer))
            .build();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }



}
