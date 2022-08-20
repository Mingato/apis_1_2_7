package com.netagentciadigital.api.controller;

import com.netagentciadigital.api.model.order.Order;
import com.netagentciadigital.api.model.order.OrderFilter;
import com.netagentciadigital.api.model.order.OrderStatusChanged;
import com.netagentciadigital.api.model.response.ApiResponseBody;
import com.netagentciadigital.api.service.OrderService;
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
@RequestMapping("/v1/order")
public class OrderController {


    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseBody> findById(@PathVariable("id") Long id){
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(orderService.findById(id))
            .build();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/list")
    public ResponseEntity<ApiResponseBody> filter(@RequestBody OrderFilter orderFilter){
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(orderService.filter(orderFilter))
                .build();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponseBody> insert(@RequestBody @Valid Order order){
        order = orderService.insert(order);
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(order)
            .build();
        result.put("orderId",order.getId());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponseBody> updateStatus(@PathVariable("id") Long id, @RequestBody @Valid OrderStatusChanged orderStatusChanged){
        Order order = orderService.updateStatus(id, orderStatusChanged);
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(order)
            .build();
        result.put("orderId",order.getId());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<ApiResponseBody> findStatus(@PathVariable("id") Long id){
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(orderService.findStatus(id))
                .build();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
