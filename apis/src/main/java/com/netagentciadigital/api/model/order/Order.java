package com.netagentciadigital.api.model.order;

import com.netagentciadigital.api.model.product.Product;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Data
@Builder
@Entity
@Table(name="order", schema = "pub")
public class Order {

    @Id
    private Long id;
    @NotBlank
    private String status;
    @NotNull
    private BigDecimal orderTotal;
    @NotNull
    private Long customerId;
    @NotNull
    private OrderShipping shipping;
    @NotNull
    private OrderPayment payment;
    @NotNull
    private List<OrderProduct> products;


    private String affiliateId;
    private String ipAddress;
    private Date datePurchased;
    private String note;

}
