package com.netagentciadigital.api.service;

import com.netagentciadigital.api.commons.exceptions.DataNotFoundException;
import com.netagentciadigital.api.model.Attribute;
import com.netagentciadigital.api.model.Product;
import com.netagentciadigital.api.model.ProductFilter;
import com.netagentciadigital.api.model.ProductQuery;
import com.netagentciadigital.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final AttributeService attributeService;


    @Autowired
    public ProductService(ProductRepository productRepository, AttributeService attributeService) {
        this.productRepository = productRepository;
        this.attributeService = attributeService;
    }


    public Product findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new DataNotFoundException("Product with id '" + id + "' not found!");
        }

        return product.get();
    }

    public List<Product> filter(ProductFilter productFilter) {
        //TODO:filter products
        return productRepository.findByNameLike("name");
    }

    public Product insert(Product product) {
        product.setId(null);
        //TODO:validate if attributes exists
        //TODO:validate if categories exists
        return productRepository.save(product);
    }

    public Product update(Long id, Product product) {
        Product productOld = findById(id);

        //TODO:alterar apenas campos diferente de null
        product.setId(productOld.getId());

        return productRepository.save(product);
    }


    public List<Product> search(ProductQuery productQuery) {
        //TODO: serach
        return null;
    }

    public List<Attribute> findAttributesByIdProduct(Long id) {
        Product product = findById(id);

        //TODO: return the right object
        return product.getAttributes();
    }

    public List<Attribute> insertAttributes(Long id) {
        //TODO: add new attribute
        return null;
    }

    public List<Attribute> updateAttributes(Long id) {
        //TODO: update new attribute
        return null;
    }
}
