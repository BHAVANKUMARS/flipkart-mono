package com.bhavan.dto.admin.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminRequest {

    private String userName;

    private String password;

    private String newPassword;

    private String confirmPassword;

}
