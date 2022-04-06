package com.server.api.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MemberDto {

    private String fullName;
    private String qrCode;
    private String relation;

    @Override
    public String toString() {
        return "{" +
                "fullName='" + fullName + '\'' +
                ", qrCode='" + qrCode + '\'' +
                ", relation='" + relation + '\'' +
                '}';
    }
}
