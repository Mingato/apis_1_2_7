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
    private Long id;
    @NotBlank
    private CustomerType type;
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
}
