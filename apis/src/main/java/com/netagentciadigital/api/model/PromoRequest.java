package com.netagentciadigital.api.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class PromoRequest {

    @NotNull
    private Long id;

    @NotNull
    private BigDecimal price_promo;

}
