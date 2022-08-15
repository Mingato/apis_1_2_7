package com.netagentciadigital.api.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@Entity
@Table(name="payment", schema = "pub")
public class Customer {

    @Id
    @NotBlank
    private String id;
    @NotBlank
    private String email;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @NotBlank
    private String cpf_cnpj;
    @NotEmpty
    private List<Phone> phone;
    @NotBlank
    private MyAddress address;

    private String gender;
    private String civil_status;
    private String dob;
    private String rg;
    private boolean status;

    @Data
    @Builder
    @Entity
    public static class Phone {
        @NotBlank
        private String ddd;
        @NotBlank
        private String telephone;
    }

    @Data
    @Builder
    @Entity
    public static class MyAddress {
        @NotBlank
        private String postcode;
        @NotBlank
        private String street;
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
    }
}
