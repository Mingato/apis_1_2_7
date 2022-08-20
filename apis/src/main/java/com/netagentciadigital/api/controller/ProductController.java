package com.netagentciadigital.api.controller;

import com.netagentciadigital.api.model.product.*;
import com.netagentciadigital.api.model.response.ApiResponseBody;
import com.netagentciadigital.api.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@Slf4j
@RestController
@RequestMapping("/v1/product")
public class ProductController {


    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ApiResponseBody> findAll(@RequestBody @Valid ProductFilter productFilter){
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(productService.filter(productFilter))
            .build();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseBody> findById(@PathVariable("id") Long id){
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(productService.findById(id))
            .build();
        result.put("productID",id);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponseBody> insert(@RequestBody @Valid Product product){
        product = productService.insert(product);
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(product)
            .build();
        result.put("productId", product.getId());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseBody> update(@PathVariable("id") Long id, @RequestBody Product product){
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(productService.update(id, product))
            .build();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponseBody> search(@RequestBody @Valid ProductQuery productQuery){
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(productService.search(productQuery))
                .build();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}/attributes")
    public ResponseEntity<ApiResponseBody> findByIdAttributes(@PathVariable("id") Long id){
        List<Attribute> attributes = productService.findAttributesByIdProduct(id);
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(attributes)
                .build();
        result.put("totalCount",attributes.size());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/{id}/attributes")
    public ResponseEntity<ApiResponseBody> insertAttributes(@PathVariable("id") Long id){
        List<Attribute> attributes = productService.insertAttributes(id);
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(attributes)
                .build();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{id}/attributes")
    public ResponseEntity<ApiResponseBody> updateAttributes(@PathVariable("id") Long id){
        List<Attribute> attributes = productService.updateAttributes(id);
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(attributes)
                .build();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/stock")
    public ResponseEntity<ApiResponseBody> getProductStock(@RequestHeader("products") String products){
        List<String> productIds = Arrays.asList(products.split(";"));

        List<StockResponse> stock = productService.findStockByIdProduct(
                productIds.stream().map(Long::getLong).collect(Collectors.toList())
        );

        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(stock)
                .build();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/stock")
    public ResponseEntity<ApiResponseBody> updateProductStock(@RequestBody List<@Valid StockRequest> stocks){
        stocks = productService.updateProductStock(stocks);
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(stocks)
                .build();
        result.put("recordsChanged", stocks.size());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/price")
    public ResponseEntity<ApiResponseBody> updateProductPrice(@RequestBody List<@Valid PriceRequest> prices){
        prices = productService.updateProductPrice(prices);
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(prices)
                .build();
        result.put("recordsChanged", prices.size());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/promo")
    public ResponseEntity<ApiResponseBody> updateProductPromo(@RequestBody List<@Valid PromoRequest> promos){
        promos = productService.updateProductPromo(promos);
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(promos)
                .build();
        result.put("recordsChanged", promos.size());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
