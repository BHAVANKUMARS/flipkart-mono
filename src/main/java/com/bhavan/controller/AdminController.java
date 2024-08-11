package com.bhavan.controller;

import com.bhavan.dto.admin.request.AdminRequest;
import com.bhavan.model.AdminDetails;
import com.bhavan.repository.admin.AdminDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    private AdminDetailsRepo adminDetailsRepo;

    @PostMapping("/create")
    public String createAdmin(@RequestBody AdminRequest adminRequest){

        AdminDetails adminDetails = AdminDetails.builder().userName(adminRequest.getUserName())
                .password(adminRequest.getPassword()).build();

        adminDetailsRepo.save(adminDetails);

        return "Record Created Successfully";

    }



}
