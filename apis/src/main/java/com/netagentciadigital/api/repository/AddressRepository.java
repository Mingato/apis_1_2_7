package com.netagentciadigital.api.repository;

import com.netagentciadigital.api.commons.utils.DBInfos;
import com.netagentciadigital.api.model.customer.MyAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * Refenrence: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
 */

@Repository
public interface AddressRepository extends JpaRepository<MyAddress, Long> {


    //TODO:query esta errada é um create
    @Query(value = "SELECT * FROM "+ DBInfos.ADDRESS_TABLE_NAME +" u "+
            "INNER JOIN " + DBInfos.ADDRESS_CUSTOMER_TABLE_NAME + " grp_usr ON u.id_usuario = grp_usr.id_usuario " +
            "where grp.cod_grp in ?1 AND grp.ativo = true AND grp.tipo = 'padrao'", nativeQuery = true)
    MyAddress saveByCustomerId(Long cid, MyAddress address);
}
