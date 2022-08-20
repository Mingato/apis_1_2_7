package com.netagentciadigital.api.service;

import com.netagentciadigital.api.commons.exceptions.DataNotFoundException;
import com.netagentciadigital.api.model.product.Attribute;
import com.netagentciadigital.api.repository.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttributeService {

    private final AttributeRepository attributeRepository;


    @Autowired
    public AttributeService(AttributeRepository attributeRepository) {
        this.attributeRepository = attributeRepository;
    }


    public List<Attribute> findAll() {
        return attributeRepository.findAll();
    }

    public Attribute findById(String id){
        Optional<Attribute> attribute = attributeRepository.findById(id);

        if(attribute.isEmpty()){
            throw new DataNotFoundException("Attribute with id '"+id+"' not found");
        }

        return attribute.get();
    }

    public Attribute insert(Attribute attribute){
        attribute.setId(null);

        return attributeRepository.save(attribute);
    }

    public Attribute update(String id, Attribute attribute){
        Attribute attribute2 = findById(id);
        attribute.setId(attribute2.getId());

        return attributeRepository.save(attribute);
    }


}
