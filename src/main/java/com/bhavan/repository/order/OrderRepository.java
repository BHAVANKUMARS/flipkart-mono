package com.bhavan.repository.order;

import com.bhavan.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderDetails,Long>, JpaSpecificationExecutor<OrderDetails> {

    List<OrderDetails> findByUserId(Long userId);
}
