package com.bhavan.service.admin;

import com.bhavan.dto.common.AmakartRequest;

public interface AdminService {

    String login(AmakartRequest loginRequest);

    String register(AmakartRequest loginRequest);

    String resetPassword(AmakartRequest loginRequest);

    Long getTotalAdminCount();


}
