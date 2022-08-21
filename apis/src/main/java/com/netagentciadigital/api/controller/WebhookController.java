package com.netagentciadigital.api.controller;

import com.netagentciadigital.api.model.response.ApiResponseBody;
import com.netagentciadigital.api.model.webhook.Webhook;
import com.netagentciadigital.api.service.WebhookService;
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
@RequestMapping("/v1/webhook")
public class WebhookController {


    private final WebhookService webhookService;

    @Autowired
    public WebhookController(WebhookService webhookService) {
        this.webhookService = webhookService;
    }

    @GetMapping
    public ResponseEntity<ApiResponseBody> findAll(){
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(webhookService.findAll())
            .build();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<ApiResponseBody> insert(@RequestBody @Valid Webhook webhook){
        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .result(webhookService.insert(webhook))
            .build();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponseBody> delete(@RequestBody @Valid Webhook webhook){
        webhookService.delete(webhook);

        ApiResponseBody result = ApiResponseBody.builder()
                .status("200")
                .build();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
