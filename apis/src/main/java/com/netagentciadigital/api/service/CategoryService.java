package com.netagentciadigital.api.service;

import com.netagentciadigital.api.commons.exceptions.DataNotFoundException;
import com.netagentciadigital.api.model.CategoryDetails;
import com.netagentciadigital.api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;


    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public CategoryDetails findById(Long id) {
        Optional<CategoryDetails> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            throw new DataNotFoundException("Category with id '" + id + "' not found!");
        }

        return category.get();
    }

    public Object findTreeById(Long id) {

        //TODO
        return null;
    }

    public CategoryDetails create(CategoryDetails category) {
        category.setId(null);

        return categoryRepository.save(category);
    }

    public CategoryDetails update(Long id, CategoryDetails category) {
        CategoryDetails categoryOld = findById(id);

        category.setId(categoryOld.getId());

        return categoryRepository.save(category);
    }

    public CategoryDetails delete(Long id) {
        CategoryDetails category = findById(id);
        categoryRepository.deleteById(id);
        return category;
    }


}
