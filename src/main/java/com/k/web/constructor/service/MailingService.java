package com.k.web.constructor.service;

import com.k.web.constructor.model.token.RegistrationToken;
import com.k.web.constructor.model.token.RegistrationTokenRepository;
import com.k.web.constructor.model.user.User;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Async(value = "mailingServiceTaskExecutor")
public class MailingService implements ApplicationListener<MailingService.UserRegistrationEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailingService.class);

    private String endpoint;

    @Autowired
    private JavaMailSenderImpl sender;
    @Autowired
    private ConfigurationService configurationService;
    @Autowired
    private RegistrationTokenRepository tokenRepository;

    @PostConstruct
    private void init() {
        endpoint = String.format("%s://%s:%d/registration/token/",
                configurationService.getScheme(), configurationService.getHost(), configurationService.getPort());
    }

    @Override
    public void onApplicationEvent(UserRegistrationEvent event) {
        User user = event.getUser();
        RegistrationToken token = createRegistrationToken(user);
        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom("Constructor");
            helper.setSubject("constructor. Registration token.");
            helper.setTo(user.getEmail());
            helper.setText("<html><body>Please <a href='" + endpoint + token.getToken()
                    + "'>verify</a> your registration.</body></html>", true);
            sender.send(message);
        } catch (Exception e) {
            LOGGER.error("Cannot send registration confirmation message to \"" + user.getEmail() + "\"", e);
        }
    }

    private RegistrationToken createRegistrationToken(User user) {
        RegistrationToken token = new RegistrationToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        token.setToken(Long.toHexString(ThreadLocalRandom.current().nextLong(Integer.MAX_VALUE, Long.MAX_VALUE)));
        token.setExpiration(Instant.now().plus(1, ChronoUnit.DAYS));

        return tokenRepository.save(token);
    }

    public static class UserRegistrationEvent extends ApplicationEvent {

        @Getter
        private User user;

        /**
         * Create a new ApplicationEvent.
         *
         * @param user the object on which the event initially occurred (never {@code null})
         */
        public UserRegistrationEvent(User user) {
            super(user);
            this.user = user;
        }
    }

}
