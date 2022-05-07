package com.server.api.member;

import lombok.Data;

@Data
public class ForgotPasswordDto {

    private String email;
    private Integer resetCode;
}
