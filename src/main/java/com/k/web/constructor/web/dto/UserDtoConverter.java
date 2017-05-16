package com.k.web.constructor.web.dto;

import com.k.web.constructor.model.user.User;
import com.k.web.constructor.web.dto.UserDto;

public class UserDtoConverter {

    public static User fromDto(UserDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRoles(dto.getRoles());
        user.setAccountNonLocked(dto.isAccountNonLocked());
        user.setId(dto.getId());
        return user;
    }

    public static UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setRoles(user.getRoles());
        dto.setAccountNonLocked(user.isAccountNonLocked());
        dto.setId(user.getId());
        return dto;
    }

}
