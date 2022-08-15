package com.netagentciadigital.api.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Builder
@Entity
@Table(name="product", schema = "pub")
public class Product {

    @Id
    private String id;

    private String name;

    private String description;

    private BigDecimal value;

    private int quantity;

}
