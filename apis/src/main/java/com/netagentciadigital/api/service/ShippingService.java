package com.netagentciadigital.api.service;

import com.netagentciadigital.api.model.shipping.Shipping;
import com.netagentciadigital.api.model.shipping.ShippingCost;
import com.netagentciadigital.api.model.shipping.ShippingCostResult;
import com.netagentciadigital.api.repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingService {

    private final ShippingRepository shippingRepository;


    @Autowired
    public ShippingService(ShippingRepository shippingRepository) {
        this.shippingRepository = shippingRepository;
    }


    public List<Shipping> findAll() {
        return shippingRepository.findAll();
    }


    public ShippingCostResult calculateShipping(String cep, ShippingCost shippingCost) {
        //TODO:Consulta o frete baseado no CEP informado, retornando as formas de entrega disponíveis
        //com os valores respectivos.
        return null;
    }

    public ShippingCostResult calculateShipping(String cep, String method, ShippingCost shippingCost) {
        //TODO:Consulta o frete baseado no CEP informado, retornando as formas de entrega disponíveis
        //com os valores respectivos.
        return null;
    }
}
