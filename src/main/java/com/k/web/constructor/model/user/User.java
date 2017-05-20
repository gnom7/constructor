package com.k.web.constructor.model.user;

import com.k.web.constructor.model.site.Site;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Data
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Column
    private boolean accountNonLocked;

    @Column
    @OneToMany(mappedBy = "user")
    private Set<Site> sites;

}
