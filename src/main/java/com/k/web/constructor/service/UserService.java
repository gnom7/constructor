package com.k.web.constructor.service;

import com.k.web.constructor.web.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void register(UserDto userDto);

    UserDto logged();

    boolean activateUser(String registrationToken);
}
