package com.bhavan.repository.customer;

import com.bhavan.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepo extends JpaRepository<ShoppingCart,Long> {
}
