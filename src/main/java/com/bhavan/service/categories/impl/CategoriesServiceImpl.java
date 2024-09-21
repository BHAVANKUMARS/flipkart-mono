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
//
//        Categories categories1= Categories.builder().id(1L).categoryName("Mobile").categoryUrl("/mobile").build();
//        Categories categories2=Categories.builder().id(2L).categoryName("Mobile").categoryUrl("/mobile").build();
//        Categories categories3 =Categories.builder().id(3L).categoryName("Mobile").categoryUrl("/mobile").build();
//        Categories categories4 =Categories.builder().id(4L).categoryName("Mobile").categoryUrl("/mobile").build();
//        Categories categories5= Categories.builder().id(5L).categoryName("Mobile").categoryUrl("/mobile").build();
//        Categories categories6=Categories.builder().id(6L).categoryName("Mobile").categoryUrl("/mobile").build();
//        Categories categories7 =Categories.builder().id(7L).categoryName("Mobile").categoryUrl("/mobile").build();
//        Categories categories8 =Categories.builder().id(8L).categoryName("Mobile").categoryUrl("/mobile").build();
//        Categories categories9= Categories.builder().id(9L).categoryName("Mobile").categoryUrl("/mobile").build();
//        Categories categories10=Categories.builder().id(10L).categoryName("Mobile").categoryUrl("/mobile").build();
//        Categories categories11 =Categories.builder().id(11L).categoryName("Mobile").categoryUrl("/mobile").build();
//        Categories categories12 =Categories.builder().id(12L).categoryName("Mobile").categoryUrl("/mobile").build();
//        Categories categories13 =Categories.builder().id(8L).categoryName("Mobile").categoryUrl("/mobile").build();
//        Categories categories14= Categories.builder().id(9L).categoryName("Mobile").categoryUrl("/mobile").build();
//        Categories categories15=Categories.builder().id(10L).categoryName("Mobile").categoryUrl("/mobile").build();
//        Categories categories16 =Categories.builder().id(11L).categoryName("Mobile").categoryUrl("/mobile").build();
//        Categories categories17 =Categories.builder().id(12L).categoryName("Mobile").categoryUrl("/mobile").build();

        List<Categories> categories = new ArrayList<>();

        for(int i=1;i<23;i++){
            Categories categories1= Categories.builder().id((long) i).categoryName("Mobile"+i).categoryUrl("/images/laptop1.jpg").build();
            categories.add(categories1);
        }

//        categories.add(categories1);
//        categories.add(categories2);
//        categories.add(categories3);
//        categories.add(categories4);
//        categories.add(categories5);
//        categories.add(categories6);
//        categories.add(categories7);
//        categories.add(categories8);
//        categories.add(categories9);
//        categories.add(categories10);
//        categories.add(categories11);
//        categories.add(categories12);
//        categories.add(categories13);
//        categories.add(categories14);
//        categories.add(categories15);
//        categories.add(categories16);
//        categories.add(categories17);

        categoriesRepo.saveAll(categories);

        return categoriesRepo.findAll();
    }

    @Override
    public Boolean deActivateCategory(Long categoryId) {
        return null;
    }
}
