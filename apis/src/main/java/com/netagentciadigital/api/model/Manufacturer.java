package com.netagentciadigital.api.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Data
@Builder
@Entity
@Table(name="manufacturer", schema = "pub")
public class Manufacturer {

    @Id
    private Long id;
    @NotBlank
    private String name;

    private Date date_added;
    private Date last_modified;
}
