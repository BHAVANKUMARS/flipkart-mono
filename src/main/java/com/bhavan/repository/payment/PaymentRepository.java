package com.bhavan.repository.payment;

import com.bhavan.model.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentDetails,Long> {

}
