package com.netagentciadigital.api.model.order;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class OrderFilterDate {

    private Date from;

    private Date to;
}
