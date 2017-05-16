package com.k.web.constructor.service;

import com.k.web.constructor.model.user.Role;
import com.k.web.constructor.model.user.User;
import com.k.web.constructor.model.user.UserRepository;
import com.k.web.constructor.web.dto.UserDto;
import com.k.web.constructor.web.dto.UserDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service(value = "userDetailsService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("admin".equals(username)) {
            UserDto dto = new UserDto();
            dto.setEmail(username);
            dto.setName(username);
            dto.setAccountNonLocked(true);
            dto.setRoles(new HashSet<>(Arrays.asList(Role.ROLE_USER, Role.ROLE_ADMIN)));
            dto.setPassword(new BCryptPasswordEncoder().encode("password"));
            return dto;
        }
        User user = userRepository.findByEmail(username);
        return UserDtoConverter.toDto(user);
    }

    @Override
    public void register(UserDto userDto) {
        userRepository.save(UserDtoConverter.fromDto(userDto));
    }

    @Override
    public UserDto logged() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        return principal instanceof UserDto ? (UserDto) principal : null;
    }

}
