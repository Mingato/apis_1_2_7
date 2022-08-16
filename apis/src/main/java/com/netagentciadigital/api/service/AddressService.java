package com.netagentciadigital.api.service;

import com.netagentciadigital.api.commons.exceptions.DataNotFoundException;
import com.netagentciadigital.api.model.MyAddress;
import com.netagentciadigital.api.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;


    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    public MyAddress findByIdCustomerAndId(Long cid, Long id) {
        Optional<MyAddress> address = addressRepository.findById(id);

        if(address.isEmpty()){
            throw new DataNotFoundException("Address with id '" + id + "' not found");
        }

        return address.get();
    }

    public MyAddress insertAddress(Long cid, MyAddress address) {
        address.setId(null);

        return addressRepository.saveByCustomerId(cid, address);
    }
}
