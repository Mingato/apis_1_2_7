package com.netagentciadigital.api.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Entity
public class MyAddress {

    @Id
    @NotBlank
    private String id;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @NotBlank
    private String postcode;
    @NotBlank
    private String address1;
    @NotNull
    private int number;
    @NotBlank
    private String complement;
    @NotBlank
    private String suburb;
    @NotBlank
    private String city;
    @NotBlank
    private String state;

    private String address2;
    private String company;
    private String isDefault;
}