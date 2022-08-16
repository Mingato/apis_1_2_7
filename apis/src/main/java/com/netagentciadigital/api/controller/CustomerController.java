package com.netagentciadigital.api.controller;

import com.netagentciadigital.api.model.Customer;
import com.netagentciadigital.api.model.MyAddress;
import com.netagentciadigital.api.model.response.ApiResponseBody;
import com.netagentciadigital.api.service.AddressService;
import com.netagentciadigital.api.service.CustomerService;
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
@RequestMapping("/v1/customer")
public class CustomerController {


    private final CustomerService customerService;

    private final AddressService addressService;

    @Autowired
    public CustomerController(CustomerService customerService, AddressService addressService) {
        this.customerService = customerService;
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<ApiResponseBody> getAll(@RequestParam(value = "name", required = false) String name){
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(customerService.filter(name))
            .build();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseBody> findById(@PathVariable("id") Long id){
        log.info(String.valueOf(id));
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(customerService.findById(id))
            .build();
        result.put("CustomerID",id);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponseBody> create(@RequestBody @Valid Customer customer){
        customer = customerService.create(customer);
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(customer)
            .build();
        result.put("customerID",customer.getId());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseBody> update(@PathVariable("id") Long id, @RequestBody @Valid Customer customer){
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(customerService.update(id, customer))
            .build();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{cid}/address/{id}")
    public ResponseEntity<ApiResponseBody> getAddress(@PathVariable("cid") Long cid, @PathVariable("id") Long id){
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(addressService.findByIdCustomerAndId(cid,id))
                .build();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/{cid}/address")
    public ResponseEntity<ApiResponseBody> createAddress(@PathVariable("cid") Long cid, @RequestBody @Valid MyAddress address){
        address = addressService.insertAddress(cid, address);
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(address)
                .build();
        result.put("customerId", cid);
        result.put("addressId", address.getId());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }



}
