package com.bhavan.service.customer.impl;

import com.bhavan.dto.common.AmakartRequest;
import com.bhavan.dto.common.AmakartResponse;
import com.bhavan.model.AdminDetails;
import com.bhavan.model.ShoppingCart;
import com.bhavan.model.UserDetails;
import com.bhavan.repository.admin.AdminDetailsRepo;
import com.bhavan.repository.customer.CustomerDetailsRepo;
import com.bhavan.repository.customer.ShoppingCartRepo;
import com.bhavan.service.customer.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDetailsRepo customerDetailsRepo;

    @Autowired
    private ShoppingCartRepo shoppingCartRepo;

    @Override
    public String login(AmakartRequest loginRequest) {

        String message = "Invalid User";

        Optional<UserDetails> adminDetails = customerDetailsRepo.findByUserNameAndPassword(loginRequest.getUserName(),loginRequest.getPassword());

        if(adminDetails.isPresent())
            message = "Valid User";


        log.info("Response "+ message);

        return message;

    }

    @Override
    public String register(AmakartRequest loginRequest) {

        String message = "Not Registered";

        boolean isUpdated = false;

        Optional<UserDetails> adminDetails = customerDetailsRepo.findByUserNameAndPassword(loginRequest.getUserName(),loginRequest.getPassword());

        if(adminDetails.isPresent()) {

            message = "User Already Exist";

        }else {


            UserDetails newAdmin = UserDetails.builder()
                    .userName(loginRequest.getUserName())
                    .password(loginRequest.getPassword())
                    .status("A").build();

            try {

                customerDetailsRepo.save(newAdmin);

                isUpdated = true;

            } catch (Exception e) {

                log.error(e.getMessage());

                isUpdated = false;

            }

            if (isUpdated)
                message = "Registered Successfully";
        }


        log.info("Response "+ message);

        return message;

    }

    @Override
    public String resetPassword(AmakartRequest loginRequest) {

        if(loginRequest.getNewPassword().equals(loginRequest.getConfirmPassword())){

            Optional<UserDetails> adminDetails = customerDetailsRepo.findByUserName(loginRequest.getUserName());

            if(adminDetails.isPresent()){

                int count = customerDetailsRepo.updatePassword(loginRequest.getUserName(), loginRequest.getConfirmPassword());

                if(count > 0)
                    return "Password Updated Successfully";
                else
                    return "Password Not Updated";

            }else {
                return "Enter a Valid Email";
            }

        }else{
            return "New Password And Confirm Password should be same";
        }


    }

    @Override
    public String addToCart(AmakartRequest shoppingCartRequest) {

        Optional<UserDetails> userDetails = customerDetailsRepo.findByUserName(shoppingCartRequest.getUserName());

        ShoppingCart shoppingCart = ShoppingCart.builder().status("A")
                .categoryId(shoppingCartRequest.getCategoryId())
                .productId(shoppingCartRequest.getProductId())
                .userId(userDetails.get().getUserId()).build();

        shoppingCartRepo.save(shoppingCart);

        return "Shopping Card Added";
    }

    @Override
    public AmakartResponse getUserDetails(String userName) {


        Optional<UserDetails> userDetails =customerDetailsRepo.findByUserName(userName);

        UserDetails userDetails1 = userDetails.get();

        AmakartResponse amakartResponse = AmakartResponse.builder().status(userDetails1.getStatus()).username(userDetails1.getUserName()).password(userDetails1.getPassword()).build();

        return amakartResponse;
    }
}
