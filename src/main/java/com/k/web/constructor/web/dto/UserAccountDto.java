package com.k.web.constructor.web.dto;

import lombok.Data;

@Data
public class UserAccountDto implements Dto {

    private String name;

    private String email;

    private String password;

}
