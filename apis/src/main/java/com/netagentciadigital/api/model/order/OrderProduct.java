package com.netagentciadigital.api.model.order;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@Entity
public class OrderProduct {

    @NotNull
    private Long id;
    @NotNull
    private int quantity;
    @NotNull
    private BigDecimal unitPrice;
    @NotNull
    private BigDecimal finalPrice;
    private boolean giftWrap = false;
    private boolean isFree = false;
}
