package com.bhavan.controller.customer;

import com.bhavan.model.Categories;
import com.bhavan.model.CategoryProductMap;
import com.bhavan.model.ProductDetails;
import com.bhavan.repository.categories.CategoriesRepo;
import com.bhavan.repository.categories.CategoryProductMapRepo;
import com.bhavan.repository.customer.CustomerDetailsRepo;
import com.bhavan.repository.product.ProductDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CategoriesRepo categoriesRepo;

    @Autowired
    private ProductDetailsRepo productDetailsRepo;

    @Autowired
    private CategoryProductMapRepo categoryProductMapRepo;

    @PostMapping("/categories")
    public List<Categories> saveAllCategories(@RequestBody List<Categories> categoriesList){

        List<Categories> categoriesDbList = categoriesRepo.saveAll(categoriesList);

        return categoriesDbList;

    }

    @PostMapping(value={"/product"})
    public List<ProductDetails> saveAllProducts(@RequestBody List<ProductDetails> productDetails){

        List<ProductDetails> productDbDetails = productDetailsRepo.saveAll(productDetails);

        return productDbDetails;

    }

    @PostMapping(value="/category/product/map")
    public List<CategoryProductMap> saveProductCategoryMap(@RequestBody List<CategoryProductMap> categoryProductMaps){

        List<CategoryProductMap> categoryProductMapsDbList = categoryProductMapRepo.saveAll(categoryProductMaps);

        return categoryProductMapsDbList;
    }

    @GetMapping(value="/products")
    public List<ProductDetails> getProductDetails(@RequestParam ("categoryId") Long categoryId){

        List<ProductDetails> productDetails = new ArrayList<>();

        List<CategoryProductMap> categoryProductMaps = categoryProductMapRepo.findByCategoryId(categoryId);

        categoryProductMaps.stream().filter(c->c.getProductId()!=null)
                .forEach(
                        cp->{

                            ProductDetails productDetail = productDetailsRepo.findById(cp.getProductId()).get();
                            productDetails.add(productDetail);
                        }
                );

        return productDetails;

    }

}
