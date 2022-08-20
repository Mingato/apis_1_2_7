package com.netagentciadigital.api.service;

import com.netagentciadigital.api.commons.exceptions.DataConflictException;
import com.netagentciadigital.api.commons.exceptions.DataNotFoundException;
import com.netagentciadigital.api.model.Manufacturer;
import com.netagentciadigital.api.model.order.Order;
import com.netagentciadigital.api.model.order.OrderFilter;
import com.netagentciadigital.api.model.order.OrderStatusChanged;
import com.netagentciadigital.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;


    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if(order.isEmpty()){
            throw new DataNotFoundException("Order with id '" + id + "' not found!");
        }

        return order.get();
    }

    public List<Order> filter(OrderFilter orderFilter) {
        //TODO: É requerido que pelo menos 1 parametro de filtro seja enviado no corpo da requisição.


        //TODO: status | ID de cadastro do status a ser definido para o pedido, OU valores predefinidos de OrderStatus

        //TODO: query | Termo de busca para filtro do resultado.cliente tal
         /*
            * Termo de busca para filtro da
            listagem.
            Pode ser enviado: número do
            pedido, nome de cliente, email,
            empresa, cidade, cep,
            telefone...
            Para pesquisar vários códigos
            de pedidos, separe-os por
            virgula: 1234,5678,9012...*/

        //TODO: date[from] date[to] Formato: YYYY-MM-DD

        //TODO: date_modified[from] date[to] Formato: YYYY-MM-DD

        //TODO: status | number Status dos pedidos para filtro.
        /*Status de pedido.
            Para obter a lista de código de
            cada status, verifique o método
            /order/status/list*/

        //TODO: shipping | Forma de entrega.

        //TODO: payment | Forma de pagamento.

        //TODO: origin | Origem do pedido.

        //TODO: complete | Pedido finalizado (concluido).

        //TODO: paid | Pedido pago.

        //TODO: order | Coluna para ordenação do resultado. orders_id order_type | Tipo de ordenação da coluna de ordem definida.
        //TODO: desc
        /*Coluna de ordenação da
            listagem.
            Valor padrão: orders_id
            Colunas aceitas: orders_id,
            customers_name,
            customers_email*/

        //TODO: order_type
        /*Tipo de ordenação da coluna
            definida.
            Valor padrão: asc
            Valores aceitos: asc (crescente)
            ou desc (decrescente)*/

        //TODO: limit | Limite
        /*Limite do resultado da consulta.
            Padrão: 50
            Exemplo: 10 ou "10,50" para
            obter registros da posição 10
            até a 50.*/

        return null;
    }

    public Order insert(Order order) {
        order.setId(null);
        //TODO: status | ID de cadastro do status a ser definido para o pedido, OU valores predefinidos de OrderStatus
        //TODO: customerId | Código de cadastro de cliente do pedido.

        //TODO: shipping[method] |  Método de entrega do pedido. (consulte as formas de entrega disponíveis).
        //TODO: shipping[cost] |  Valor do custo de frete do pedido
        //TODO: shipping[address] |  Endereço de entrega do pedido.

        //TODO: payment[method]  |  Método de pagamento do pedido (consulte as formas de pagamento disponíveis).

        //TODO: affiliateId  |  ID de cadastro do parceiro. OU valores predefinidos de OrderAffiliateId

        //TODO: ipAddress  |  Endereço IP de realização do pedido.

        //TODO: datePurchased | Data de realização do pedido.

        return orderRepository.save(order);
    }

    public Order updateStatus(Long id, @Valid OrderStatusChanged status) {
        Order order = findById(id);
        //TODO: status | ID de cadastro do status a ser definido para o pedido, OU valores predefinidos de OrderStatus

        //TODO: track_code | Código de rastreamento, caso tenha.

        //TODO: nfe | Dados da Nota Fiscal do pedido.

        //TODO: comments | Comentários/Informações a respeito deste novo status.

        //TODO: isViewable | O cliente poderá visualizar o status.

        order.setStatus(status.getStatus_id());

        return order;
    }

    public List<OrderStatusChanged> findStatus(Long id) {
        //TODO: O status são totalmente gerenciados pelo Lojista, será necessário obter esta lista para
        //utilização dos IDs de cadastro nos gerenciamentos de pedidos.
        return null;
    }
}
