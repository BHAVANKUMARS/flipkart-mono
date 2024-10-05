package com.bhavan.repository.order;

import com.bhavan.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderDetails,Long> {

    List<OrderDetails> findByUserId(Long userId);
}
