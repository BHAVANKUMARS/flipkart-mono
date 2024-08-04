package com.bhavan.service.admin;

import com.bhavan.dto.admin.request.AdminRequest;

public interface AdminService {

    boolean login(AdminRequest loginRequest);

    boolean register(AdminRequest loginRequest);

    String resetPassword(AdminRequest loginRequest);


}
