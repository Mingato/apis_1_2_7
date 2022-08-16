package com.netagentciadigital.api.service;

import com.netagentciadigital.api.commons.exceptions.DataNotFoundException;
import com.netagentciadigital.api.model.Customer;
import com.netagentciadigital.api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;


    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public Customer findById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isEmpty()){
            throw new DataNotFoundException("Customer with id '" + id + "' not found!");
        }

        return customer.get();
    }

    public List<Customer> filter(String name) {
        return customerRepository.findByNameLike(name);
    }

    public Customer create(Customer customer) {
        customer.setId(null);

        Customer customerWithSameEmail = customerRepository.findByEmail(customer.getEmail());
        return Objects.requireNonNullElseGet(customerWithSameEmail, () -> customerRepository.save(customer));
    }

    public Customer update(Long id, Customer customer) {
        Customer customerOld = findById(id);

        customer.setId(customerOld.getId());

        return customerRepository.save(customer);
    }

    public Customer delete(Long id) {
        Customer customer = findById(id);
        customerRepository.deleteById(id);
        return customer;
    }

}
