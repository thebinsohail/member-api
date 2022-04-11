package com.server.api.member;

import lombok.*;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Member {


    private String id;
    private String qrCode;
    private String fullName;
    private String relation;

}
