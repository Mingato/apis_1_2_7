package com.netagentciadigital.api.controller;

import com.netagentciadigital.api.model.MyPagination;
import com.netagentciadigital.api.model.response.ApiResponseBody;
import com.netagentciadigital.api.service.AddressService;
import com.netagentciadigital.api.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@Slf4j
@RestController
@RequestMapping("/v1/mailing")
public class CustomerMailingController {


    private final CustomerService customerService;

    @Autowired
    public CustomerMailingController(CustomerService customerService, AddressService addressService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public ResponseEntity<ApiResponseBody> findAll(@RequestBody MyPagination myPagination){
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(customerService.findMailing(myPagination))
            .build();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
