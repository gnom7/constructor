package com.k.web.constructor.service.converter;

import com.k.web.constructor.model.user.User;
import com.k.web.constructor.web.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDtoConverter implements Converter<User, UserDto> {

    @Override
    public UserDto convert(User user) {
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRoles(user.getRoles());
        userDto.setAccountNonLocked(user.isAccountNonLocked());
        userDto.setId(user.getId());
        return userDto;
    }

}
