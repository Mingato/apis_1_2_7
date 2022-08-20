package com.netagentciadigital.api.controller;

import com.netagentciadigital.api.model.shipping.ShippingCost;
import com.netagentciadigital.api.model.response.ApiResponseBody;
import com.netagentciadigital.api.service.ShippingService;
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
@RequestMapping("/v1/shipping")
public class ShippingController {


    private final ShippingService shippingService;

    @Autowired
    public ShippingController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @GetMapping
    public ResponseEntity<ApiResponseBody> findAll(){
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(shippingService.findAll())
            .build();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping("/{cep}")
    public ResponseEntity<ApiResponseBody> calculateShipping(@PathVariable("cep") String cep,
                                                             @RequestBody @Valid ShippingCost shippingCost){
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(shippingService.calculateShipping(cep, shippingCost))
            .build();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/{cep}/{method}")
    public ResponseEntity<ApiResponseBody> calculateShipping(@PathVariable("cep") String cep,
                                                             @PathVariable("method") String method,
                                                             @RequestBody @Valid ShippingCost shippingCost){
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(shippingService.calculateShipping(cep, method, shippingCost))
                .build();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
