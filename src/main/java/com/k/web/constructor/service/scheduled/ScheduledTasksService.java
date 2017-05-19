package com.k.web.constructor.service.scheduled;

import com.k.web.constructor.model.token.RegistrationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * @author a.kolenchenko
 * @since 19.05.17
 */
@Service
public class ScheduledTasksService {

    @Autowired
    private RegistrationTokenRepository tokenRepository;

    public void removeExpiredTokensFromDB() {
        tokenRepository.deleteAllByExpirationBefore(Instant.now());
    }

}
