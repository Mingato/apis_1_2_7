package com.netagentciadigital.api.repository;

import com.netagentciadigital.api.model.webhook.Webhook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * Refenrence: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
 */

@Repository
public interface WebhookRepository extends JpaRepository<Webhook, Long> {


    List<Webhook> findByUrl(String url);
}
