package com.k.web.constructor.service;

import com.k.web.constructor.event.UserRegistrationEvent;
import com.k.web.constructor.model.token.RegistrationToken;
import com.k.web.constructor.model.token.RegistrationTokenRepository;
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

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

@Service(value = "userDetailsService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RegistrationTokenRepository tokenRepository;
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

    @PostConstruct
    private void init() {
        if (Objects.isNull(userRepository.findByUsername("admin"))) {
            User user = new User();
            user.setEmail("admin");
            user.setUsername("admin");
            user.setAccountNonLocked(true);
            user.setRoles(new HashSet<>(Arrays.asList(Role.ROLE_USER, Role.ROLE_ADMIN)));
            user.setPassword(new BCryptPasswordEncoder().encode("password"));
            userRepository.save(user);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return userToUserDtoConverter.convert(user);
    }

    @Override
    public void register(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = userRepository.save(userDtoToUserConverter.convert(userDto));
        UserRegistrationEvent event = new UserRegistrationEvent(user);
        eventPublisher.publishEvent(event);
    }

    @Override
    public UserDto logged() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        return principal instanceof UserDto ? (UserDto) principal : null;
    }

    @Override
    public boolean activateUser(String registrationToken) {
        RegistrationToken token = tokenRepository.findByToken(registrationToken);
        if (Objects.nonNull(token) && ChronoUnit.MINUTES.between(Instant.now(), token.getExpiration()) > 0) {
            User user = token.getUser();
            user.setAccountNonLocked(true);
            userRepository.save(user);
            tokenRepository.delete(token);
            return true;
        } else {
            return false;
        }
    }

}
