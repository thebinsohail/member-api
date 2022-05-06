package com.server.api.member;

import lombok.Data;

@Data
public class ForgotPasswordDto {

    private String oldPassword;
    private String newPassword;
}
