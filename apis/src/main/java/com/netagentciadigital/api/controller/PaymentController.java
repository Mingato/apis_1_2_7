package com.netagentciadigital.api.controller;

import com.netagentciadigital.api.model.response.ApiResponseBody;
import com.netagentciadigital.api.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@Slf4j
@RestController
@RequestMapping("/v1/payment")
public class PaymentController {


    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public ResponseEntity<ApiResponseBody> getAll(){
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(paymentService.findAll())
            .build();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
