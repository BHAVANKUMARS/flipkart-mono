package com.bhavan.repository.customer;

import com.bhavan.model.AdminDetails;
import com.bhavan.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerDetailsRepo extends JpaRepository<UserDetails,Long>, JpaSpecificationExecutor<UserDetails> {

    Optional<UserDetails> findByUserNameAndPassword(String userName, String password);

    Optional<UserDetails> findByUserName(String userName);

    @Query(value = "UPDATE admin_details SET password =:password WHERE user_name = :userName",nativeQuery = true)
    int updatePassword(@Param("userName")String userName, @Param("password") String password);

    Long countByStatus(String status);

    List<UserDetails> findByStatus(String status);


}
