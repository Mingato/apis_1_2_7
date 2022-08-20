package com.netagentciadigital.api.service;

import com.netagentciadigital.api.model.webhook.Webhook;
import com.netagentciadigital.api.repository.WebhookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebhookService {

    private final WebhookRepository webhookRepository;


    @Autowired
    public WebhookService(WebhookRepository webhookRepository) {
        this.webhookRepository = webhookRepository;
    }

    public List<Webhook> findAll() {
        return webhookRepository.findAll();
    }

    public List<Webhook> findByUrl(String url) {
        return webhookRepository.findByUrl(url);
    }


    public Webhook insert(Webhook webhook) {
        webhook.setId(null);

        if(findByUrl(webhook.getUrl()).isEmpty()){
            return webhookRepository.save(webhook);
        }

        return findByUrl(webhook.getUrl()).get(0);
    }

    public void delete(Webhook webhook) {
        List<Webhook> webhooks = findByUrl(webhook.getUrl());

        for(Webhook webhook1: webhooks){
            if(webhook1.getAction().equals(webhook.getAction()) &&
                webhook1.getType().equals(webhook.getType())){
                webhookRepository.deleteById(webhook1.getId());
                return;
            }
        }
    }


}
