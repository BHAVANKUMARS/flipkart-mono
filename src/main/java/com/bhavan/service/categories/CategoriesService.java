package com.bhavan.service.categories;

import com.bhavan.model.Categories;

import java.util.List;

public interface CategoriesService {

    List<Categories> findAllCategories();

    Boolean deActivateCategory(Long categoryId);

}
