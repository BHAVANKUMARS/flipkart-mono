package com.bhavan.service.customer.impl;

import com.bhavan.CustomerSpecification;
import com.bhavan.ProductSpecification;
import com.bhavan.dto.common.AmakartRequest;
import com.bhavan.dto.common.AmakartResponse;
import com.bhavan.model.AdminDetails;
import com.bhavan.model.ProductDetails;
import com.bhavan.model.ShoppingCart;
import com.bhavan.model.UserDetails;
import com.bhavan.repository.admin.AdminDetailsRepo;
import com.bhavan.repository.customer.CustomerDetailsRepo;
import com.bhavan.repository.customer.ShoppingCartRepo;
import com.bhavan.repository.product.ProductDetailsRepo;
import com.bhavan.service.customer.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDetailsRepo customerDetailsRepo;

    @Autowired
    private ShoppingCartRepo shoppingCartRepo;

    @Autowired
    private ProductDetailsRepo productDetailsRepo;

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

        Optional<ShoppingCart> existShoppingCart  = shoppingCartRepo.findByUserIdAndProductId(userDetails.get().getUserId() ,shoppingCartRequest.getProductId());

        if(!existShoppingCart.isPresent()) {
            ShoppingCart shoppingCart = ShoppingCart.builder().status("A")
                    .categoryId(shoppingCartRequest.getCategoryId())
                    .productId(shoppingCartRequest.getProductId())
                    .userId(userDetails.get().getUserId()).build();

            shoppingCartRepo.save(shoppingCart);

            return "Shopping Card Added";
        }else
            return "Already Added in Shopping Card";
    }

    @Override
    public List<ProductDetails> getCartDetail(String userName) {

        Optional<UserDetails> userDetails = customerDetailsRepo.findByUserName(userName);

        List<ShoppingCart> shoppingCartList = shoppingCartRepo.findByUserId(userDetails.get().getUserId());

        List<ProductDetails> productDetailsList = new ArrayList<>();

        shoppingCartList.stream().filter(shoppingCart -> shoppingCart.getProductId()!=null)
                .forEach(shoppingCart -> {

                    ProductDetails productDetails = productDetailsRepo.findById(shoppingCart.getProductId()).get();
                    productDetailsList.add(productDetails);

                });

        return productDetailsList;
    }

    @Override
    public AmakartResponse getUserDetails(String userName) {


        Optional<UserDetails> userDetails =customerDetailsRepo.findByUserName(userName);

        UserDetails userDetails1 = userDetails.get();

        AmakartResponse amakartResponse = AmakartResponse.builder().status(userDetails1.getStatus()).username(userDetails1.getUserName()).password(userDetails1.getPassword()).build();

        return amakartResponse;
    }

    @Override
    public Long getCustomerTotalCount() {
        return customerDetailsRepo.countByStatus("A");
    }

    @Override
    public List<UserDetails> getActiveUsers() {
        return customerDetailsRepo.findByStatus("A");
    }

    @Override
    public List<UserDetails> searchCriteria(Map<String, String> params) {
        Specification<UserDetails> spec = CustomerSpecification.searchByCriteria(params);
        return customerDetailsRepo.findAll(spec);
    }
}
