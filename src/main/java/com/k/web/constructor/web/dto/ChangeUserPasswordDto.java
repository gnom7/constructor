package com.k.web.constructor.web.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ChangeUserPasswordDto implements Dto {

    @NotNull
    private String email;

    @NotNull
    private String oldPassword;

    @NotNull
    private String newPassword;

    @NotNull
    private String newPasswordConfirmation;

}
