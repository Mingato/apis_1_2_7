package com.netagentciadigital.api.service;

import com.netagentciadigital.api.model.shipping.Shipping;
import com.netagentciadigital.api.model.shipping.ShippingCostRequest;
import com.netagentciadigital.api.model.shipping.ShippingCostResponse;
import com.netagentciadigital.api.repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Null;
import java.util.List;

@Validated
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

    /**
     *  https://www.erlaniofreire.com.br/web/post/calcular-frete-com-a-api-dos-correios/62*/
    public ShippingCostResponse calculateShipping(
            Long cep,
            @Valid @Null @Min(3) @Max(20) String method,
            ShippingCostRequest shippingCost) {

        //TODO: https://www.erlaniofreire.com.br/web/post/calcular-frete-com-a-api-dos-correios/62
        //https://dev.freterapido.com/ecommerce/verificacao_de_credenciais/
        //TODO:Consulta o frete baseado no CEP informado, retornando as formas de entrega disponíveis
        //com os valores respectivos.
        return null;
    }
}
