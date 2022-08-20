package com.netagentciadigital.api.model.order;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Entity
public class OrderPayment {

    @NotBlank
    private String method;

    @NotNull
    private int installments;
}
