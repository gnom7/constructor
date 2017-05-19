package com.k.web.constructor.service.converter;

import com.k.web.constructor.model.user.User;
import com.k.web.constructor.web.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToUserConverter implements Converter<UserDto, User> {

    @Override
    public User convert(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRoles(userDto.getRoles());
        user.setAccountNonLocked(userDto.isAccountNonLocked());
        user.setId(userDto.getId());
        return user;
    }
}
