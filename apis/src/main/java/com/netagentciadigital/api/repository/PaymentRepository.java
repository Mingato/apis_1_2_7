package com.netagentciadigital.api.repository;

import com.netagentciadigital.api.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * Refenrence: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
 */

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {


}
