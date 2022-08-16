package com.netagentciadigital.api.controller;

import com.netagentciadigital.api.model.CategoryDetails;
import com.netagentciadigital.api.model.Customer;
import com.netagentciadigital.api.model.MyAddress;
import com.netagentciadigital.api.model.response.ApiResponseBody;
import com.netagentciadigital.api.service.CategoryService;
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
@RequestMapping("/v1/category")
public class CategoryController {


    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {

        this.categoryService = categoryService;
    }


    @GetMapping("/tree/{id}")
    public ResponseEntity<ApiResponseBody> findTreeById(@PathVariable("id") Long id){
        log.info(String.valueOf(id));
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(categoryService.findTreeById(id))
                .build();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseBody> findById(@PathVariable("id") Long id){
        log.info(String.valueOf(id));
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(categoryService.findById(id))
            .build();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponseBody> create(@RequestBody @Valid CategoryDetails category){
        category = categoryService.create(category);
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(category)
            .build();
        result.put("categ_id",category.getId());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseBody> update(@PathVariable("id") Long id, @RequestBody @Valid CategoryDetails category){
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(categoryService.update(id, category))
            .build();
        result.put("categ_id", id);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
