package com.netagentciadigital.api.repository;

import com.netagentciadigital.api.model.CategoryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * Refenrence: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
 */

@Repository
public interface CategoryRepository extends JpaRepository<CategoryDetails, Long> {

    List<CategoryDetails> findByTitleLike(String title);

}
