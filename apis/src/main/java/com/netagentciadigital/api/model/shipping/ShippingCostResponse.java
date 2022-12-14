package com.netagentciadigital.api.model.shipping;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Builder
public class ShippingCostResponse {

    private String code;
    private String title;
    private String image;
    private String description;
    private BigDecimal cost;

}
