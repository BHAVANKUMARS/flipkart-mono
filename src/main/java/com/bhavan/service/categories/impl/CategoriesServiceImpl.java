package com.bhavan.service.categories.impl;

import com.bhavan.model.Categories;
import com.bhavan.repository.categories.CategoriesRepo;
import com.bhavan.service.categories.CategoriesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    private CategoriesRepo categoriesRepo;

    @Override
    public List<Categories> findAllCategories() {
        return categoriesRepo.findAll();
    }

    @Override
    public Boolean deActivateCategory(Long categoryId) {
        return null;
    }
}
