package com.bhavan.repository.categories;

import com.bhavan.model.CategoryProductMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryProductMapRepo extends JpaRepository<CategoryProductMap,Long> {

    List<CategoryProductMap> findByCategoryId(Long categoryId);


}
