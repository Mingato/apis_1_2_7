package com.netagentciadigital.api.model.order;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@Entity
public class OrderStatusChanged {

    @Id
    private Long id;

    @NotBlank
    private String status_id;

    private String track_code;

    private String nfe;

    private String comments;

    private Boolean isViewable;

}
