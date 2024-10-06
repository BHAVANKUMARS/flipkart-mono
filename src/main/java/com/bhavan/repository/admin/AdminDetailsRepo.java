package com.bhavan.repository.admin;

import com.bhavan.model.AdminDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AdminDetailsRepo extends JpaRepository<AdminDetails,Long> {


    Optional<AdminDetails> findByUserNameAndPassword(String userName,String password);

    Optional<AdminDetails> findByUserName(String userName);

    Long countByStatus(String status);

    @Query(value = "UPDATE admin_details SET password =:password WHERE user_name = :userName",nativeQuery = true)
    int updatePassword(@Param("userName")String userName, @Param("password") String password);

}
