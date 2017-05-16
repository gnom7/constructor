package com.k.web.constructor.web.dto;

import com.k.web.constructor.model.user.Role;
import com.k.web.constructor.web.dto.Dto;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UserDto implements UserDetails, CredentialsContainer, Dto {

    private Long id;

    @Size(min = 4, max = 20)
    private String name;

    @Email
    private String email;

    @Size(max = 30)
    private String password;

    private Set<Role> roles;

    private boolean accountNonLocked;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(Role::name).map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }
}
