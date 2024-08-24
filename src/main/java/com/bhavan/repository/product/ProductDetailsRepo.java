package com.bhavan.repository.product;

import com.bhavan.model.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailsRepo extends JpaRepository<ProductDetails,Long> {


}
