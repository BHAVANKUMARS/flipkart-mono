package com.bhavan.repository.categories;

import com.bhavan.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepo extends JpaRepository<Categories,Long> {
}
