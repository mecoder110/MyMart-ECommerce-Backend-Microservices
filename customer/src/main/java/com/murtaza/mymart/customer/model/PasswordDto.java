package com.murtaza.mymart.customer.model;

import lombok.Data;

@Data
public class PasswordDto {

    private String email;
    private String currentPwd;
    private String newPwd;
    private String confirmPwd;
}
