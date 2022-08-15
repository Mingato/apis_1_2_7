package com.netagentciadigital.api.model;


import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@Entity
@Table(name="shipping", schema = "pub")
public class ShippingCost {


    @NotNull
    private BigDecimal order_total;
    @NotNull
    private BigDecimal order_weight;

    private List<ProductShipping> products;


    @Data
    @Builder
    public static class ProductShipping {
        @NotNull
        private int quantity;
        @NotNull
        private BigDecimal weight;
        @NotBlank
        private String dimensions;
        @NotNull
        private boolean free_shipping;
        @NotBlank
        private String block_shipping;

    }
}
