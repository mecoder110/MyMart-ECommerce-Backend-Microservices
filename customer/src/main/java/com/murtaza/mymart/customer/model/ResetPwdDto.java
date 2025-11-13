package com.murtaza.mymart.customer.model;

import lombok.Data;

@Data
public class ResetPwdDto {

    private String email;
    private String currentPwd;
    private String newPwd;
    private String confirmPwd;
}
