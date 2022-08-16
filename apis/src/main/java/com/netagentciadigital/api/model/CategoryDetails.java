package com.netagentciadigital.api.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@Entity
@Table(name="category", schema = "pub")
public class CategoryDetails {

    @Id
    private Long id;
    @NotBlank
    @Size(min=4, max=50)
    private String name;
    @Size(min=3)
    private String subtitle;
    private int sort_order;
    private Long parent_id = 0L;
    private int discount;
    private boolean hidden;



    private String seo_title;
    private String seo_keywords;
    private String seo_description;
    private String slug;
}
