package com.k.web.constructor.service;

import com.k.web.constructor.model.token.RegistrationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

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

    @Component
    public static class RemoveExpiredTokensFromDBTrigger implements Trigger {
        @Override
        public Date nextExecutionTime(TriggerContext triggerContext) {
            return Date.from(Instant.now().plus(1, ChronoUnit.DAYS));
        }
    }

}
