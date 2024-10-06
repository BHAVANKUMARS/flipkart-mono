package com.bhavan.service.admin.impl;

import com.bhavan.dto.common.AmakartRequest;
import com.bhavan.model.AdminDetails;
import com.bhavan.service.admin.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bhavan.repository.admin.AdminDetailsRepo;

import java.util.Optional;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDetailsRepo adminDetailsRepo;

    @Override
    public String login(AmakartRequest loginRequest) {

        String message = "Invalid User";

        Optional<AdminDetails> adminDetails = adminDetailsRepo.findByUserNameAndPassword(loginRequest.getUserName(),loginRequest.getPassword());

         if(adminDetails.isPresent())
             message = "Valid User";


         log.info("Response "+ message);

         return message;

    }

    @Override
    public String register(AmakartRequest loginRequest) {

        String message = "Not Registered";

        boolean isUpdated = false;

        Optional<AdminDetails> adminDetails = adminDetailsRepo.findByUserNameAndPassword(loginRequest.getUserName(),loginRequest.getPassword());

        if(adminDetails.isPresent()) {

            message = "Already Exist";

        }else {


            AdminDetails newAdmin = AdminDetails.builder()
                    .userName(loginRequest.getUserName())
                    .password(loginRequest.getPassword())
                    .status("A")
                    .build();

            try {

                adminDetailsRepo.save(newAdmin);

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

    @Override
    public Long getTotalAdminCount() {
        return adminDetailsRepo.countByStatus("A");
    }
}
