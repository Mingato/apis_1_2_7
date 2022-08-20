package com.netagentciadigital.api.model.product;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class StockResponse {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private int quantity;

    @NotNull
    private TypeStock checkStock;

    @NotNull
    private List<AttributesStock> attributes;

    @Data
    @Builder
    public static class AttributesStock {
        private Long id;
        private boolean status;
        private String cod_ref;
        private int quantity;
    }
}
