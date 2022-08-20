package com.netagentciadigital.api.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class StockRequest {

    @NotNull
    private Long id;

    @NotNull
    private TypeStock type;

    @NotNull
    private int quantity;


}
