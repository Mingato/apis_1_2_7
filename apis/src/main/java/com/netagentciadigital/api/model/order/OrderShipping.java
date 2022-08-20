package com.netagentciadigital.api.model.order;

import com.netagentciadigital.api.model.customer.MyAddress;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@Builder
@Entity
public class OrderShipping {

    private String title;

    @NotBlank
    private String method;
    @NotNull
    private BigDecimal cost;

    private Date scheduleDate;

    @NotNull
    private MyAddress address;
}
