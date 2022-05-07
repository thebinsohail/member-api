package com.server.api.member;

import lombok.Data;


@Data
public class Member {

    private String id;
    private String qrCode;
    private String email;
    private String fullName;
    private String password;
    private Integer resetCode;
    private String relation;

}
