package com.netagentciadigital.api.service;

import com.netagentciadigital.api.commons.exceptions.DataConflictException;
import com.netagentciadigital.api.commons.exceptions.DataNotFoundException;
import com.netagentciadigital.api.model.Manufacturer;
import com.netagentciadigital.api.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;


    @Autowired
    public ManufacturerService(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }


    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    public Manufacturer findById(Long id) {
        Optional<Manufacturer> manufacturer = manufacturerRepository.findById(id);
        if(manufacturer.isEmpty()){
            throw new DataNotFoundException("Manufacturer with id '" + id + "' not found!");
        }

        return manufacturer.get();
    }

    public Manufacturer insert(Manufacturer manufacturer) {
        manufacturer.setId(null);
        manufacturer.setDate_added(new Date(System.currentTimeMillis()));
        manufacturer.setLast_modified(null);
        if(manufacturerRepository.findByName(manufacturer.getName()).isEmpty()) {
            return manufacturerRepository.save(manufacturer);
        }

        throw new DataConflictException("Manufacturer with name '"+manufacturer.getName()+"' already exists!");
    }

    public Manufacturer update(Long id, Manufacturer manufacturer) {
        Manufacturer manufacturerOld = findById(id);

        manufacturer.setId(manufacturerOld.getId());
        manufacturer.setDate_added(manufacturerOld.getDate_added());
        manufacturer.setLast_modified(new Date(System.currentTimeMillis()));

        if(manufacturerRepository.findByName(manufacturer.getName()).isEmpty()) {
            return manufacturerRepository.save(manufacturer);
        }

        throw new DataConflictException("Manufacturer with name '"+manufacturer.getName()+"' already exists!");
    }

}
