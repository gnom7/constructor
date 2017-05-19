package com.k.web.constructor.model.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.temporal.TemporalAccessor;

@Repository
public interface RegistrationTokenRepository extends JpaRepository<RegistrationToken, Long> {

    RegistrationToken findByToken(String token);

    void deleteAllByExpirationBefore(TemporalAccessor time);

}
