package com.bhavan.service.impl;

import com.bhavan.dto.admin.request.AdminRequest;
import com.bhavan.model.AdminDetails;
import com.bhavan.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bhavan.repository.admin.AdminDetailsRepo;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDetailsRepo adminDetailsRepo;

    @Override
    public boolean login(AdminRequest loginRequest) {

        Optional<AdminDetails> adminDetails = adminDetailsRepo.findByUserNameAndPassword(loginRequest.getUserName(),loginRequest.getPassword());

        return adminDetails.isPresent();
    }

    @Override
    public boolean register(AdminRequest loginRequest) {

        AdminDetails adminDetails = AdminDetails.builder()
                                            .userName(loginRequest.getUserName())
                                            .password(loginRequest.getPassword()).build();

        adminDetails = adminDetailsRepo.save(adminDetails);

        return adminDetails.getAdminId()!=null;

    }

    @Override
    public String resetPassword(AdminRequest loginRequest) {

        if(loginRequest.getNewPassword().equals(loginRequest.getConfirmPassword())){

            Optional<AdminDetails> adminDetails = adminDetailsRepo.findByUserName(loginRequest.getUserName());

            if(adminDetails.isPresent()){

                int count = adminDetailsRepo.updatePassword(loginRequest.getUserName(), loginRequest.getConfirmPassword());

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
}
