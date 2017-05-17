package com.k.web.constructor.validation;

import com.k.web.constructor.model.user.User;
import com.k.web.constructor.model.user.UserRepository;
import com.k.web.constructor.web.dto.ChangeUserPasswordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

import static com.k.web.constructor.util.Constants.MIN_PASSWORD_SIZE;
import static com.k.web.constructor.util.Constants.USER_CHANGE_PASSWORD_WRONG_EMAIL;
import static com.k.web.constructor.util.Constants.USER_CHANGE_PASSWORD_WRONG_NEW_PASSWORD;
import static com.k.web.constructor.util.Constants.USER_CHANGE_PASSWORD_WRONG_OLD_PASSWORD;
import static com.k.web.constructor.util.Constants.USER_CHANGE_PASSWORD_WRONG_PASSWORD_CONFIRMATION;

@Component
public class ChangeUserPasswordDtoValidator implements Validator {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean supports(Class<?> clazz) {
        return ChangeUserPasswordDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Objects.requireNonNull(errors, "Null error object in validator args.");

        ChangeUserPasswordDto dto = (ChangeUserPasswordDto) target;
        String email = dto.getEmail();
        String oldPassword = dto.getOldPassword();
        String newPassword = dto.getNewPassword();
        String newPasswordConfirmation = dto.getNewPasswordConfirmation();

        User user = userRepository.findByEmail(email);

        if (Objects.isNull(user)) {
            errors.rejectValue("email", USER_CHANGE_PASSWORD_WRONG_EMAIL);
        }

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            errors.rejectValue("oldPassword", USER_CHANGE_PASSWORD_WRONG_OLD_PASSWORD);
        }

        if (newPassword.matches(".*\\s+.*") || newPassword.length() < MIN_PASSWORD_SIZE) {
            errors.rejectValue("newPassword", USER_CHANGE_PASSWORD_WRONG_NEW_PASSWORD);
        }

        if (Objects.equals(newPassword, newPasswordConfirmation)) {
            errors.rejectValue("newPasswordConfirmation", USER_CHANGE_PASSWORD_WRONG_PASSWORD_CONFIRMATION);
        }

    }

}
