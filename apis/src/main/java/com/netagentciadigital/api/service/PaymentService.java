package com.netagentciadigital.api.service;

import com.netagentciadigital.api.model.Payment;
import com.netagentciadigital.api.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;


    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

}
