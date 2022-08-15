package com.netagentciadigital.api.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Builder
@Entity
@Table(name="shipping", schema = "pub")
public class Shipping {

    @Id
    private String id;

    private String title;

    private String image;

    private String description;

    private boolean status;

}
