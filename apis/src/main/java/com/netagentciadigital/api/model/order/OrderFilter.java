package com.netagentciadigital.api.model.order;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Data
@Builder
public class OrderFilter {

    private String query;

    private OrderFilterDate date;

    private OrderFilterDate date_modified;

    @Min(value = 0)
    @Max(value = 1)
    private Integer status;

    private String shipping;

    private String order_type;

    private String origin;

    private Boolean complete;

    private Boolean paid;

    private String order;

    private Integer limit;
}
