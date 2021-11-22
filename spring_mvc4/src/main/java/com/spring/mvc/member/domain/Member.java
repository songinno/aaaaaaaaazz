package com.spring.mvc.member.domain;

import lombok.*;

import java.util.Date;
@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder // 원하는것만 써서 생성할 수 있음. (Test에서)
public class Member {

    private String account;
    private String password;
    private String name;
    private String email;
    private Auth auth;
    private Date regDate;


}
