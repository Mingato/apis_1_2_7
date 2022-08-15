package com.netagentciadigital.api.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@Entity
@Table(name="payment", schema = "pub")
public class Payment {

    @Id
    private String id;
    private String type;
    private String title;
    private String image;
    private boolean status;

}
