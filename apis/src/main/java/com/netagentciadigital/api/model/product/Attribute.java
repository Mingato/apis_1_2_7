package com.netagentciadigital.api.model.product;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Builder
@Entity
@Table(name="attribute", schema = "pub")
public class Attribute {

    @Id
    private String id;
    @NotBlank
    private String groupName;

    private int isDefault;

    private String cod_ref;

    private int stock;

    private int quantity;

    private List<AttributeOptions> options;

    @Data
    @Builder
    @Entity
    public class AttributeOptions {
        private AttributeOptionsType type;
        private String name;
        private String value;
        private int optionSort;
        private int valueSort;
    }
}
