package com.k.web.constructor.service;

import com.k.web.constructor.model.user.Role;
import com.k.web.constructor.model.user.User;
import com.k.web.constructor.model.user.UserRepository;
import com.k.web.constructor.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service(value = "userDetailsService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private Converter<User, UserDto> userToUserDtoConverter;
    @Autowired
    private Converter<UserDto, User> userDtoToUserConverter;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

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
        return userToUserDtoConverter.convert(user);
    }

    @Override
    public void register(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = userRepository.save(userDtoToUserConverter.convert(userDto));
        MailingService.UserRegistrationEvent event = new MailingService.UserRegistrationEvent(user);
        eventPublisher.publishEvent(event);
    }

    @Override
    public UserDto logged() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        return principal instanceof UserDto ? (UserDto) principal : null;
    }

}
