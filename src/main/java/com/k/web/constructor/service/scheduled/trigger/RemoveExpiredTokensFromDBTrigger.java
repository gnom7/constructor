package com.k.web.constructor.service.scheduled.trigger;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author a.kolenchenko
 * @since 19.05.17
 */
@Component
public class RemoveExpiredTokensFromDBTrigger implements Trigger {

    @Override
    public Date nextExecutionTime(TriggerContext triggerContext) {
        return Date.from(Instant.now().plus(1, ChronoUnit.DAYS));
    }

}
