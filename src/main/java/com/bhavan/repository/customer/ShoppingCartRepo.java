package com.bhavan.repository.customer;

import com.bhavan.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartRepo extends JpaRepository<ShoppingCart,Long> {

    Optional<ShoppingCart> findByUserIdAndProductId(Long userId,Long productId);

    List<ShoppingCart> findByUserId(Long userId);

}
