package com.k.web.constructor.model.token;

import com.k.web.constructor.model.user.User;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "REGISTRATION_TOKEN")
@Data
public class RegistrationToken {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Instant expiration;

    @Column
    private String token;

    @ManyToOne
    private User user;

}
