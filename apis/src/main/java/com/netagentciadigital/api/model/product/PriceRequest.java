package com.netagentciadigital.api.model.product;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class PriceRequest {

    @NotNull
    private Long id;

    @NotNull
    private BigDecimal new_price;

}
