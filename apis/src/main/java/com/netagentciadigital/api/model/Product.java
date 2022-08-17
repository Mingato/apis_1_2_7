package com.netagentciadigital.api.model;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Entity
@Table(name="product", schema = "pub")
public class Product {

    @Id
    private Long id;

    @NotBlank
    private String name;
    @NotNull
    @Range(min=0,max=1)
    private Integer status;
    @NotNull
    private BigDecimal price;
    @NotNull
    private Integer quantity;

    private String cod_ref;
    private String gtin;
    private String mpn;
    private String ncm;
    private String model;
    private String check_stock = "product";
    private boolean allow_buy = true;
    private BigDecimal weight;
    private List<Integer> dimensions;
    private int quantity_max_buy = 0;
    private int quantity_min_buy = 0;
    private int quantity_critical = 0;
    private int handling_days = 0;
    private String description_small;
    private String description_full;
    private String description_mobile;
    private String manufacturer_id;
    private String availability;
    private List<Integer> categories;
    private List<Attribute> attributes;
    private Long attrGroupId;
    private boolean allowGiftWrap = false;
    private boolean isFreeShipping = false;
    private boolean highlight = false;
    private boolean hotDeal = false;
    private boolean newRelease = false;
    private boolean exclusive = false;
    private String slug;
    private String seo_title;
    private String seo_description;
    private String seo_keywords;
    private List<String> images;
    private String specifications;

    private Date dateAddedFrom;
}
