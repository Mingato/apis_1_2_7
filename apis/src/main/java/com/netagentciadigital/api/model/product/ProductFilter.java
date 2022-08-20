package com.netagentciadigital.api.model.product;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class ProductFilter {

    private int page = 1;
    private int perPage = 20;
    private ProductOrder order = ProductOrder.products_id;
    private ProductOrderType orderType = ProductOrderType.desc;

    private Date dateAddedFrom;
    private Long categId;
    private int stockLessThan;
    private int stockGreaterThan;
    private boolean freeShipping;
    private boolean status;


}
