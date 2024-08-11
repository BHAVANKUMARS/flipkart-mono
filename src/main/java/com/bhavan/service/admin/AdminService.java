package com.bhavan.service.admin;

import com.bhavan.dto.admin.request.AdminRequest;

public interface AdminService {

    String login(AdminRequest loginRequest);

    String register(AdminRequest loginRequest);

    String resetPassword(AdminRequest loginRequest);


}
