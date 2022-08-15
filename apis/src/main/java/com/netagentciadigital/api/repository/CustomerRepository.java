package com.netagentciadigital.api.repository;

import com.netagentciadigital.api.model.Customer;
import com.netagentciadigital.api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * Refenrence: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
 */

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    Customer findByEmail(String email);

    List<Customer> findByNameLike(String name);

}
