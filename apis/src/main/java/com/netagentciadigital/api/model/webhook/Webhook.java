package com.netagentciadigital.api.model.webhook;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Entity
@Table(name="webhook", schema = "pub")
public class Webhook {

    @Id
    private Long id;

    @NotNull
    private WebhookType type;

    @NotNull
    private WebhookAction action;

    @NotBlank
    private String url;
}
