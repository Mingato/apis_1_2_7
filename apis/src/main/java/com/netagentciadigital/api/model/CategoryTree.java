package com.netagentciadigital.api.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@Entity
public class CategoryTree {

    @Id
    private Long id;

    private String name;

    private String img;
    private String img_h;

    private int level;
    private long parent_id;

    private List<CategoryTree> subCategories;

}
